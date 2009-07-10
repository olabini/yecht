/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public class Node {
    long id;
    KindTag kind;
    String type_id;
    String anchor;
    Data data;
    Object shortcut;
    
    // syck_alloc_map
    public static Node allocMap() {
        Data.Map m = new Data.Map();
        m.style = MapStyle.None;
        m.idx = 0;
        m.capa = YAML.ALLOC_CT;
        m.keys = new long[m.capa];
        m.values = new long[m.capa];
        
        Node n = KindTag.Map.allocNode();
        n.data = m;
        return n;
    }

    // syck_alloc_seq
    public static Node allocSeq() {
        Data.Seq s = new Data.Seq();
        s.style = SeqStyle.None;
        s.idx = 0;
        s.capa = YAML.ALLOC_CT;
        s.items = new long[s.capa];
        
        Node n = KindTag.Seq.allocNode();
        n.data = s;
        return n;
    }

    // syck_alloc_str
    public static Node allocStr() {
        Data.Str s = new Data.Str();
        s.style = ScalarStyle.None;
        s.ptr = Pointer.nullPointer();
        s.len = 0;
        
        Node n = KindTag.Str.allocNode();
        n.data = s;
        return n;
    }

    // syck_new_str
    // syck_new_str2
    public static Node newStr(Pointer str, int len, ScalarStyle style) {
        Node n = allocStr();
        Data.Str s = (Data.Str)n.data;
        s.ptr = Pointer.create(new byte[len], 0);
        s.len = len;
        s.style = style;
        str.memcpy(s.ptr, len);
        return n;
    }

    // syck_replace_str
    // syck_replace_str2
    public static void replaceStr(Node n, Pointer str, int len, ScalarStyle style) {
        Data.Str s = (Data.Str)n.data;
        s.ptr = Pointer.create(new byte[len], 0);
        s.len = len;
        s.style = style;
        str.memcpy(s.ptr, len);
    }

    /*
void
syck_str_blow_away_commas( SyckNode *n )
{
    char *go, *end;

    go = n->data.str->ptr;
    end = go + n->data.str->len;
    while ( *(++go) != '\0' )
    {
        if ( *go == ',' )
        {
            n->data.str->len -= 1;
            memmove( go, go + 1, end - go );
            end -= 1;
        }
    }
}

char *
syck_str_read( SyckNode *n )
{
    ASSERT( n != NULL );
    return n->data.str->ptr;
}

SyckNode *
syck_new_map( SYMID key, SYMID value )
{
    SyckNode *n;

    n = syck_alloc_map();
    syck_map_add( n, key, value );

    return n;
}

void
syck_map_empty( SyckNode *n )
{
    struct SyckMap *m;
    ASSERT( n != NULL );
    ASSERT( n->data.list != NULL );

    S_FREE( n->data.pairs->keys );
    S_FREE( n->data.pairs->values );
    m = n->data.pairs;
    m->idx = 0;
    m->capa = ALLOC_CT;
    m->keys = S_ALLOC_N( SYMID, m->capa );
    m->values = S_ALLOC_N( SYMID, m->capa );
}

void
syck_map_add( SyckNode *map, SYMID key, SYMID value )
{
    struct SyckMap *m;
    long idx;

    ASSERT( map != NULL );
    ASSERT( map->data.pairs != NULL );
    
    m = map->data.pairs;
    idx = m->idx;
    m->idx += 1;
    if ( m->idx > m->capa )
    {
        m->capa += ALLOC_CT;
        S_REALLOC_N( m->keys, SYMID, m->capa );
        S_REALLOC_N( m->values, SYMID, m->capa );
    }
    m->keys[idx] = key;
    m->values[idx] = value;
}

void
syck_map_update( SyckNode *map1, SyckNode *map2 )
{
    struct SyckMap *m1, *m2;
    long new_idx, new_capa;
    ASSERT( map1 != NULL );
    ASSERT( map2 != NULL );

    m1 = map1->data.pairs;
    m2 = map2->data.pairs;
    if ( m2->idx < 1 ) return;
        
    new_idx = m1->idx;
    new_idx += m2->idx;
    new_capa = m1->capa;
    while ( new_idx > new_capa )
    {
        new_capa += ALLOC_CT;
    }
    if ( new_capa > m1->capa )
    {
        m1->capa = new_capa;
        S_REALLOC_N( m1->keys, SYMID, m1->capa );
        S_REALLOC_N( m1->values, SYMID, m1->capa );
    }
    for ( new_idx = 0; new_idx < m2->idx; m1->idx++, new_idx++ )
    {
        m1->keys[m1->idx] = m2->keys[new_idx]; 
        m1->values[m1->idx] = m2->values[new_idx]; 
    }
}

long
syck_map_count( SyckNode *map )
{
    ASSERT( map != NULL );
    ASSERT( map->data.pairs != NULL );
    return map->data.pairs->idx;
}

void
syck_map_assign( SyckNode *map, enum map_part p, long idx, SYMID id )
{
    struct SyckMap *m;

    ASSERT( map != NULL );
    m = map->data.pairs;
    ASSERT( m != NULL );
    if ( p == map_key )
    {
        m->keys[idx] = id;
    }
    else
    {
        m->values[idx] = id;
    }
}

SYMID
syck_map_read( SyckNode *map, enum map_part p, long idx )
{
    struct SyckMap *m;

    ASSERT( map != NULL );
    m = map->data.pairs;
    ASSERT( m != NULL );
    if ( p == map_key )
    {
        return m->keys[idx];
    }
    else
    {
        return m->values[idx];
    }
}

SyckNode *
syck_new_seq( SYMID value )
{
    SyckNode *n;

    n = syck_alloc_seq();
    syck_seq_add( n, value );

    return n;
}

void
syck_seq_empty( SyckNode *n )
{
    struct SyckSeq *s;
    ASSERT( n != NULL );
    ASSERT( n->data.list != NULL );

    S_FREE( n->data.list->items );
    s = n->data.list;
    s->idx = 0;
    s->capa = ALLOC_CT;
    s->items = S_ALLOC_N( SYMID, s->capa );
}

void
syck_seq_add( SyckNode *arr, SYMID value )
{
    struct SyckSeq *s;
    long idx;

    ASSERT( arr != NULL );
    ASSERT( arr->data.list != NULL );
    
    s = arr->data.list;
    idx = s->idx;
    s->idx += 1;
    if ( s->idx > s->capa )
    {
        s->capa += ALLOC_CT;
        S_REALLOC_N( s->items, SYMID, s->capa );
    }
    s->items[idx] = value;
}

long
syck_seq_count( SyckNode *seq )
{
    ASSERT( seq != NULL );
    ASSERT( seq->data.list != NULL );
    return seq->data.list->idx;
}

void
syck_seq_assign( SyckNode *seq, long idx, SYMID id )
{
    struct SyckSeq *s;

    ASSERT( map != NULL );
    s = seq->data.list;
    ASSERT( m != NULL );
    s->items[idx] = id;
}

SYMID
syck_seq_read( SyckNode *seq, long idx )
{
    struct SyckSeq *s;

    ASSERT( seq != NULL );
    s = seq->data.list;
    ASSERT( s != NULL );
    return s->items[idx];
}

     */
}// Node
