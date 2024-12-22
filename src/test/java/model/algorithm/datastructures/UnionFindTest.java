package model.algorithm.datastructures;

import org.junit.*;

import static org.junit.Assert.*;

public class UnionFindTest {

    @Test
    public void testInitialization() {
        int n = 5;
        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            assertEquals(i, uf.find(i)); //cada elemento ha de ser identificado por sí mismo en el principio
        }
    }

    @Test
    public void testSimpleUnion() {
        UnionFind uf = new UnionFind(5);

        uf.union(0, 1);

        assertEquals(uf.find(0), uf.find(1));
        assertNotEquals(uf.find(0), uf.find(2)); //vemos si el union funciona
    }

    @Test
    public void testIndirectUnion() {
        UnionFind uf = new UnionFind(4);

        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 2);

        assertEquals(uf.find(0), uf.find(3));
    }

    @Test
    public void testFindWithPathCompression() {
        UnionFind uf = new UnionFind(6);

        //0 -> 1 -> 2 -> 3
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);

        int root = uf.find(3);

        //miramos si todos los nodos apuntan a la raiz (path compression)
        assertEquals(root, uf.find(0));
        assertEquals(root, uf.find(1));
        assertEquals(root, uf.find(2));
        assertEquals(root, uf.find(3));
    }

    @Test
    public void testMultipleWeightedUnions() { //miramos varios unions y verificamos que se cumpla el wieghted union
        UnionFind uf = new UnionFind(13);

        //conjunto A (0,1,2)
        uf.union(0, 1); uf.union(1, 2);

        //conjunto B (3,4,8,9,10,11)
        uf.union(3, 4); uf.union(4, 8); uf.union(4, 9);
        uf.union(4, 10); uf.union(4, 11);

        //conjunto C (5,6,7)
        uf.union(5, 6);
        uf.union(6, 7);

        //conjunto D (12)
        int rootB = uf.find(3);
        //unimos los conjuntos, todos deberian congar del conjunto B
        uf.union(3, 0); uf.union(3, 5); uf.union(3, 12);

        for (int i = 0; i < 8; i++) {
            assertEquals(rootB, uf.find(i));
        }
    }

    @Test
    public void testUnionWithHiself() {
        UnionFind uf = new UnionFind(2);
        uf.union(1, 1);
        assertEquals(1, uf.find(1));
    }

    @Test
    public void testEdgeCaseSingleElement() {
        UnionFind uf = new UnionFind(1);
        assertEquals(0, uf.find(0));
    }

    @Test
    public void testEdgeCaseDisconnectedElements() {
        UnionFind uf = new UnionFind(4);

        //miramos si estan desconectados
        assertNotEquals(uf.find(0), uf.find(1));
        assertNotEquals(uf.find(2), uf.find(3));
    }

    @Test
    public void testEdgeCaseLargeNumberOfElements() {
        int n = 10000;
        UnionFind uf = new UnionFind(n);

        //unimos todos los elementos
        for (int i = 0; i < n - 1; i++) {
            uf.union(i, i + 1);
        }

        //miramos si se han unido
        for (int i = 0; i < n; i++) {
            assertEquals(uf.find(0), uf.find(i));
        }
    }
}
