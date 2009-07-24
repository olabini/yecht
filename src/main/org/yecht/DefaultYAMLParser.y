%{
package org.yecht;

public class DefaultYAMLParser {

%}

%start doc

%token        YAML_ANCHOR YAML_ALIAS YAML_TRANSFER YAML_TAGURI YAML_ITRANSFER
%token        YAML_WORD YAML_PLAIN YAML_BLOCK
%token        YAML_DOCSEP YAML_IOPEN YAML_INDENT YAML_IEND

%left               '-' ':'
%left               '[' ']' '{' '}' ',' '?'

%%
doc     : atom {
           parser.root = parser.addNode((Node)$1);
        }
        | YAML_DOCSEP atom_or_empty {
           parser.root = parser.addNode((Node)$2);
        }
        | {
           parser.eof = true;
        }
        ;

atom	: word_rep
        | ind_rep
        ;

ind_rep : struct_rep
        | YAML_TRANSFER ind_rep { 
            Parser.addTransfer((String)$1, (Node)$2, parser.taguri_expansion);
            $$ = $2;
        }
        | YAML_TAGURI ind_rep {
            Parser.addTransfer((String)$1, (Node)$2, false);
            $$ = $2;
        }
        | YAML_ANCHOR ind_rep { 
           /*
            * _Anchors_: The language binding must keep a separate symbol table
            * for anchors.  The actual ID in the symbol table is returned to the
            * higher nodes, though.
            */
           $$ = parser.addAnchor((String)$1, (Node)$2 );
        }
        | indent_open ind_rep indent_flex_end {
           $$ = $2;
        }
        ;

atom_or_empty   : atom
                | empty
                ;

empty           : indent_open empty indent_end {
                    $$ = $2;
                }
                | {
                    Node n = NULL_NODE( parser );
                    $$ = n;
                }
                | YAML_ITRANSFER empty
                { 
                   if(parser.implicit_typing)
                   {
                      ImplicitScanner.tryTagImplicit((Node)$2, parser.taguri_expansion);
                   }
                   $$ = $2;
                }
                | YAML_TRANSFER empty
                { 
                    Parser.addTransfer((String)$1, (Node)$2, parser.taguri_expansion);
                    $$ = $2;
                }
                | YAML_TAGURI empty
                {
                    Parser.addTransfer((String)$1, (Node)$2, false);
                    $$ = $2;
                }
                | YAML_ANCHOR empty
                { 
                   /*
                    * _Anchors_: The language binding must keep a separate symbol table
                    * for anchors.  The actual ID in the symbol table is returned to the
                    * higher nodes, though.
                    */
                   $$ = parser.addAnchor((String)$1, (Node)$2 );
                }
                ;

/*
 * Indentation abstractions
 */
indent_open     : YAML_IOPEN
                | indent_open YAML_INDENT
                ;
                
indent_end      : YAML_IEND
                ;

indent_sep      : YAML_INDENT
                ;

indent_flex_end : YAML_IEND
                | indent_sep indent_flex_end
                ;

/*
 * Words are broken out to distinguish them
 * as keys in implicit maps and valid elements
 * for the inline structures
 */
word_rep	: YAML_TRANSFER word_rep						
            { 
               Parser.addTransfer((String)$1, (Node)$2, parser.taguri_expansion);
               $$ = $2;
            } 
            | YAML_TAGURI word_rep
            { 
               Parser.addTransfer((String)$1, (Node)$2, false);
               $$ = $2;
            } 
            | YAML_ITRANSFER word_rep						
            { 
               if(parser.implicit_typing)
               {
                  ImplicitScanner.tryTagImplicit((Node)$2, parser.taguri_expansion);
               }
               $$ = $2;
            }
            | YAML_ANCHOR word_rep
            { 
               $$ = parser.addAnchor((String)$1, (Node)$2 );
            }
            | YAML_ALIAS										
            {
               /*
                * _Aliases_: The anchor symbol table is scanned for the anchor name.
                * The anchor's ID in the language's symbol table is returned.
                */
               $$ = parser.getAnchor((String)$1);
            }
			| YAML_WORD
            { 
               Node n = (Node)$1;
               if(parser.taguri_expansion) {
                   n.type_id = Parser.taguri(YAML.DOMAIN, "str");
               } else {
                   n.type_id = "str";
               }
               $$ = n;
            }
            | YAML_PLAIN
            | indent_open word_rep indent_flex_end
            {
               $$ = $2;
            }
            ;

/*
 * Any of these structures can be used as
 * complex keys
 */
struct_rep	: YAML_BLOCK
			| implicit_seq
			| inline_seq
			| implicit_map
			| inline_map
            ;

/*
 * Implicit sequence 
 */
implicit_seq	: indent_open top_imp_seq indent_end
                { 
                    $$ = $2;
                }
                | indent_open in_implicit_seq indent_end
                { 
                    $$ = $2;
                }
                ;

basic_seq       : '-' atom_or_empty             
                { 
                    $$ = parser.addNode((Node) $2);
                }
                ;

top_imp_seq     : YAML_TRANSFER indent_sep in_implicit_seq
                { 
                    Parser.addTransfer((String)$1, (Node)$3, parser.taguri_expansion);
                    $$ = $3;
                }
                | YAML_TRANSFER top_imp_seq
                { 
                    Parser.addTransfer((String)$1, (Node)$2, parser.taguri_expansion);
                    $$ = $2;
                }
                | YAML_TAGURI indent_sep in_implicit_seq
                { 
                    Parser.addTransfer((String)$1, (Node)$3, false);
                    $$ = $3;
                }
                | YAML_TAGURI top_imp_seq
                { 
                    Parser.addTransfer((String)$1, (Node)$2, false);
                    $$ = $2;
                }
                | YAML_ANCHOR indent_sep in_implicit_seq
                { 
                    $$ = parser.addAnchor((String)$1, (Node)$3 );
                }
                | YAML_ANCHOR top_imp_seq
                { 
                    $$ = parser.addAnchor((String)$1, (Node)$2 );
                }
                ;

in_implicit_seq : basic_seq
                {
                    $$ = Node.newSeq($1);
                }
				| in_implicit_seq indent_sep basic_seq
				{ 
                    ((Node)$1).seqAdd($3);
                    $$ = $1;
				}
				| in_implicit_seq indent_sep
				{ 
                    $$ = $1;
				}
                ;

/*
 * Inline sequences
 */
inline_seq		: '[' in_inline_seq ']'
                { 
                    $$ = $2;
                }
				| '[' ']'
                { 
                    $$ = Node.allocSeq();
                }
                ;

in_inline_seq   : inline_seq_atom
                {
                    $$ = Node.newSeq(parser.addNode((Node)$1));
                }
                | in_inline_seq ',' inline_seq_atom
				{ 
                    ((Node)$1).seqAdd(parser.addNode((Node)$3));
                    $$ = $1;
				}
                ;

inline_seq_atom : atom
                | basic_mapping
                ;

/*
 * Implicit maps
 */
implicit_map	: indent_open top_imp_map indent_end
                { 
                    applySeqInMap(parser, (Node)$2);
                    $$ = $2;
                }
                | indent_open in_implicit_map indent_end
                { 
                    applySeqInMap(parser, (Node)$2);
                    $$ = $2;
                }
                ;

top_imp_map     : YAML_TRANSFER indent_sep in_implicit_map
                { 
                    Parser.addTransfer((String)$1, (Node)$3, parser.taguri_expansion);
                    $$ = $3;
                }
                | YAML_TRANSFER top_imp_map
                { 
                    Parser.addTransfer((String)$1, (Node)$2, parser.taguri_expansion);
                    $$ = $2;
                }
                | YAML_TAGURI indent_sep in_implicit_map
                { 
                    Parser.addTransfer((String)$1, (Node)$3, false);
                    $$ = $3;
                }
                | YAML_TAGURI top_imp_map
                { 
                    Parser.addTransfer((String)$1, (Node)$2, false);
                    $$ = $2;
                }
                | YAML_ANCHOR indent_sep in_implicit_map
                { 
                    $$ = parser.addAnchor((String)$1, (Node)$3);
                }
                | YAML_ANCHOR top_imp_map
                { 
                    $$ = parser.addAnchor((String)$1, (Node)$2);
                }
                ;

complex_key     : word_rep
                | '?' atom indent_sep
                {
                    $$ = $2;
                }
                ;

complex_value   : atom_or_empty
                ;

complex_mapping : complex_key ':' complex_value
                {
                    $$ = Node.newMap( 
                        parser.addNode( (Node)$1 ), 
                        parser.addNode( (Node)$3 ) );
                }
                ;

in_implicit_map : complex_mapping
				| in_implicit_map indent_sep basic_seq
                {
                    if ( ((Node)$1).shortcut == null )
                    {
                        ((Node)$1).shortcut = Node.newSeq($3);
                    }
                    else
                    {
                        ((Node)((Node)$1).shortcut).seqAdd($3);
                    }
                    $$ = $1;
                }
				| in_implicit_map indent_sep complex_mapping
                {
                    applySeqInMap( parser, (Node)$1 );
                    ((Node)$1).mapUpdate((Node)$3);
                    $3 = null;
                    $$ = $1;
                }
				| in_implicit_map indent_sep
                {
                    $$ = $1;
                }
                ;

/*
 * Inline maps
 */
basic_mapping	: atom ':' atom_or_empty
                {
                    $$ = Node.newMap( 
                        parser.addNode( (Node)$1 ), 
                        parser.addNode( (Node)$3 ) );
                }
                ;

inline_map		: '{' in_inline_map '}'
                {
                    $$ = $2;
                }
          		| '{' '}'
                {
                    $$ = Node.allocMap();
                }
                ;
         
in_inline_map	: inline_map_atom
				| in_inline_map ',' inline_map_atom
				{
                    ((Node)$1).mapUpdate((Node)$3);
                    $3 = null;
                    $$ = $1;
				}
                ;

inline_map_atom : atom
                {
                    Node n = NULL_NODE( parser );
                    $$ = Node.newMap( 
                        parser.addNode( (Node)$1 ), 
                        parser.addNode( n ));
                }
                | basic_mapping
                ;
%%

    private Parser parser;
    public DefaultYAMLParser(Parser parser) {
        this.parser = parser;
    }

    public static Node NULL_NODE(Parser parser) {
        Node n = Node.newStr(Pointer.create(new byte[0], 0), 0, ScalarStyle.Plain);
        if(parser.taguri_expansion) {
            n.type_id = Parser.taguri(YAML.DOMAIN, "null");
        } else {
            n.type_id = "null";
        }
        return n;
    }


    public static void applySeqInMap(Parser parser, Node n) {
        if(n.shortcut == null) {
            return;
        }

        int map_len = (int)n.mapCount();
        n.mapAssign(MapPart.Value, map_len - 1, parser.addNode((Node)n.shortcut));
        n.shortcut = null;
    }

    public void yyerror(String msg) {
        TokenScanner.error(msg, parser);
    }
}
