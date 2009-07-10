/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

import java.util.Map;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public class Parser {
    private Parser() {}

    long root, root_on_error;
    boolean implicit_typing, taguri_expansion;
    NodeHandler handler;
    ErrorHandler error_handler;
    BadAnchorHandler bad_anchor_handler;
    ParserInput input_type;
    IOType io_type;
    int bufsize;
    Pointer buffer, linectptr, lineptr, toktmp, token, cursor, marker, limit;
    int linect;
    int last_token;
    int force_token;
    int eof;
    JechtIO io;
    Map anchors, bad_anchors;
    Map syms;
    Level[] levels;
    int lvl_idx;
    int lvl_capa;
    Object bonus;

    // syck_parser_reset_levels
    public void resetLevels() {
        while(lvl_idx > 1) {
            popLevel();
        }

        if(lvl_idx < 1) {
            lvl_idx = 1;
            levels[0].spaces = -1;
            levels[0].ncount = 0;
            levels[0].domain = "";
        }
        levels[0].status = LevelStatus.header;
    }

    // syck_parser_pop_level
    public void popLevel() {
        if(lvl_idx <= 1) {
            return;
        }
        lvl_idx--;
    }

    // syck_parser_reset_cursor
    public void resetCursor() {
        if(buffer == null) {
            buffer = Pointer.create(new byte[bufsize], 0);
        }
        buffer.buffer[buffer.start] = 0;
        cursor = null;
        lineptr = null;
        linectptr = null;
        token = null;
        toktmp = null;
        marker = null;
        limit = null;

        root = 0;
        root_on_error = 0;
        linect = 0;
        eof = 0;
        last_token = 0;
        force_token = 0;
    }

    // syck_parser_set_root_on_error
    public void setRootOnError(long roer) {
        root_on_error = roer;
    }

    // syck_new_parser
    public static Parser newParser() {
        Parser p = new Parser();
        p.lvl_capa = YAML.ALLOC_CT;
        p.levels = new Level[p.lvl_capa];
        p.input_type = ParserInput.YAML_UTF8;
        p.io_type = IOType.Str;
        p.io = null;
        p.syms = null;
        p.anchors = null;
        p.bad_anchors = null;
        p.implicit_typing = true;
        p.taguri_expansion = false;
        p.bufsize = YAML.BUFFERSIZE;
        p.buffer = null;
        p.lvl_idx = 0;
        p.resetLevels();
        return p;
    }

    // syck_add_sym
    public int addSym(Object data) {
        // TODO: implement when I know how the symbols are actually used
        return -1;
    }

    // syck_lookup_sym
    public Object lookupSym(long id) {
        // TODO: implement when I know how the symbols are actually used
        return null;
    }

    // syck_parser_handler
    public void handler(NodeHandler hdlr) {
        handler = hdlr;
    }

    // syck_parser_implicit_typing
    public void implicitTyping(boolean flag) {
        implicit_typing = flag;
    }

    // syck_parser_taguri_expansion
    public void taguriExpansion(boolean flag) {
        taguri_expansion = flag;
    }
    
    // syck_parser_error_handler
    public void errorHandler(ErrorHandler hdlr) {
        error_handler = hdlr;
    }

    // syck_parser_bad_anchor_handler
    public void badAnchorHandler(BadAnchorHandler hdlr) {
        bad_anchor_handler = hdlr;
    }

    // syck_parser_set_input_type
    public void setInputType(ParserInput input_type) {
        this.input_type = input_type;
    }


// void
// syck_parser_file( SyckParser *p, FILE *fp, SyckIoFileRead read )
// {
//     ASSERT( p != NULL );
//     free_any_io( p );
// 	syck_parser_reset_cursor( p );
//     p->io_type = syck_io_file;
//     p->io.file = S_ALLOC( SyckIoFile );
//     p->io.file->ptr = fp;
//     if ( read != NULL )
//     {
//         p->io.file->read = read;
//     }
//     else
//     {
//         p->io.file->read = syck_io_file_read;
//     }
// }

// void
// syck_parser_str( SyckParser *p, const char *ptr, long len, SyckIoStrRead read )
// {
//     ASSERT( p != NULL );
//     free_any_io( p );
// 	syck_parser_reset_cursor( p );
//     p->io_type = syck_io_str;
//     p->io.str = S_ALLOC( SyckIoStr );
//     p->io.str->beg = ptr;
//     p->io.str->ptr = ptr;
//     p->io.str->end = ptr + len;
//     if ( read != NULL )
//     {
//         p->io.str->read = read;
//     }
//     else
//     {
//         p->io.str->read = syck_io_str_read;
//     }
// }

// void
// syck_parser_str_auto( SyckParser *p, const char *ptr, SyckIoStrRead read )
// {
//     syck_parser_str( p, ptr, strlen( ptr ), read );
// }

// SyckLevel *
// syck_parser_current_level( SyckParser *p )
// {
//     return &p->levels[p->lvl_idx-1];
// }

// void 
// syck_parser_add_level( SyckParser *p, int len, enum syck_level_status status )
// {
//     ASSERT( p != NULL );
//     if ( p->lvl_idx + 1 > p->lvl_capa )
//     {
//         p->lvl_capa += ALLOC_CT;
//         S_REALLOC_N( p->levels, SyckLevel, p->lvl_capa );
//     }

//     ASSERT( len > p->levels[p->lvl_idx-1].spaces );
//     p->levels[p->lvl_idx].spaces = len;
//     p->levels[p->lvl_idx].ncount = 0;
//     p->levels[p->lvl_idx].domain = syck_strndup( p->levels[p->lvl_idx-1].domain, strlen( p->levels[p->lvl_idx-1].domain ) );
//     p->levels[p->lvl_idx].status = status;
//     p->lvl_idx += 1;
// }

// long
// syck_move_tokens( SyckParser *p )
// {
//     long count, skip;
//     ASSERT( p->buffer != NULL );

//     if ( p->token == NULL )
//         return 0;

//     skip = p->limit - p->token;
//     if ( skip < 0 )
//         return 0;

//     if ( ( count = p->token - p->buffer ) )
//     {
//         S_MEMMOVE( p->buffer, p->token, char, skip );
//         p->token = p->buffer;
//         p->marker -= count;
//         p->cursor -= count;
//         p->toktmp -= count;
//         p->limit -= count;
//         p->lineptr -= count;
//         p->linectptr -= count;
//     }
//     return skip;
// }

// void
// syck_check_limit( SyckParser *p, long len )
// {
//     if ( p->cursor == NULL )
//     {
//         p->cursor = p->buffer;
//         p->lineptr = p->buffer;
//         p->linectptr = p->buffer;
//         p->marker = p->buffer;
//     }
//     p->limit = p->buffer + len;
// }

// long
// syck_parser_read( SyckParser *p )
// {
//     long len = 0;
//     long skip = 0;
//     ASSERT( p != NULL );
//     switch ( p->io_type )
//     {
//         case syck_io_str:
//             skip = syck_move_tokens( p );
//             len = (p->io.str->read)( p->buffer, p->io.str, SYCK_BUFFERSIZE - 1, skip );
//             break;

//         case syck_io_file:
//             skip = syck_move_tokens( p );
//             len = (p->io.file->read)( p->buffer, p->io.file, SYCK_BUFFERSIZE - 1, skip );
//             break;
//     }
//     syck_check_limit( p, len );
//     return len;
// }

// long
// syck_parser_readlen( SyckParser *p, long max_size )
// {
//     long len = 0;
//     long skip = 0;
//     ASSERT( p != NULL );
//     switch ( p->io_type )
//     {
//         case syck_io_str:
//             skip = syck_move_tokens( p );
//             len = (p->io.str->read)( p->buffer, p->io.str, max_size, skip );
//             break;

//         case syck_io_file:
//             skip = syck_move_tokens( p );
//             len = (p->io.file->read)( p->buffer, p->io.file, max_size, skip );
//             break;
//     }
//     syck_check_limit( p, len );
//     return len;
// }

// SYMID
// syck_parse( SyckParser *p )
// {
//     ASSERT( p != NULL );

//     syck_st_free( p );
//     syck_parser_reset_levels( p );
//     syckparse( p );
//     return p->root;
// }

// void
// syck_default_error_handler( SyckParser *p, const char *msg )
// {
//     printf( "Error at [Line %d, Col %d]: %s\n", 
//         p->linect,
//         p->cursor - p->lineptr,
//         msg );
// }
}// Parser
