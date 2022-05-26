package material.tree.linkedtree;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import material.tree.narytree.LinkedTree;
import material.Position;
import material.tree.util.*;

/**
 *
 * @author Abraham Duarte
 */
public class TreeUitlsTest extends TestCase {
	
	public void testFront() {
		LinkedTree<String> arbol = new LinkedTree<String>();
		Position<String> raiz = arbol.addRoot("A");
		Position<String> nodo1 = arbol.add("B", raiz);
		Position<String> nodo2 = arbol.add("C", raiz);
		Position<String> nodo3 = arbol.add("D", nodo1);
		Position<String> nodo4 = arbol.add("E", nodo2);
		Position<String> nodo5 = arbol.add("F", nodo2);
		Position<String> nodo6 = arbol.add("G", nodo2);
		Position<String> nodo7 = arbol.add("H", nodo5);
		Position<String> nodo8 = arbol.add("I", nodo5);
                                
		List<Position<String>> leafnodes = new ArrayList<Position<String>>();
		leafnodes.add(nodo3);
		leafnodes.add(nodo4);
		leafnodes.add(nodo7);
		leafnodes.add(nodo8);
		leafnodes.add(nodo6);
                
                FrontierIterator <String> fi = new FrontierIterator<>(arbol);
		while (fi.hasNext()) {
                    Position<String> leaf = fi.next();
                    assertTrue(leafnodes.contains(leaf));
		}
		assertFalse(leafnodes.contains(nodo1));
		assertFalse(leafnodes.contains(nodo2));
		assertFalse(leafnodes.contains(nodo5));
		assertFalse(leafnodes.contains(raiz));
	}
	
	public void testDepth() {
		LinkedTree<String> arbol = new LinkedTree<String>();
                DepthCalculator dp = new DepthCalculator();

		assertEquals(dp.calculate(arbol), 0);
		Position<String> raiz = arbol.addRoot("A");
		assertEquals(dp.calculate(arbol), 1);
		Position<String> nodo1 = arbol.add("B", raiz);
		assertEquals(dp.calculate(arbol), 2);
		Position<String> nodo2 = arbol.add("C", raiz);
		assertEquals(dp.calculate(arbol), 2);
		Position<String> nodo3 = arbol.add("D", nodo1);
		assertEquals(dp.calculate(arbol), 3);
		Position<String> nodo4 = arbol.add("E", nodo2);
		assertEquals(dp.calculate(arbol), 3);
		Position<String> nodo5 = arbol.add("F", nodo2);
		assertEquals(dp.calculate(arbol), 3);
		Position<String> nodo6 = arbol.add("G", nodo2);
		assertEquals(dp.calculate(arbol), 3);
		Position<String> nodo7 = arbol.add("H", nodo5);
		assertEquals(dp.calculate(arbol), 4);
		Position<String> nodo8 = arbol.add("I", nodo5);
		assertEquals(dp.calculate(arbol), 4);
		arbol.remove(nodo5);
		assertEquals(dp.calculate(arbol), 3);
	}

	public void testDegree() {
            
                DegreeCalculator dg = new DegreeCalculator();
            
		LinkedTree<String> arbol = new LinkedTree<String>();
		assertEquals(dg.calculate(arbol), 0);
		Position<String> raiz = arbol.addRoot("A");
		assertEquals(dg.calculate(arbol), 1);
		Position<String> nodo1 = arbol.add("B", raiz);
		assertEquals(dg.calculate(arbol), 1);
		Position<String> nodo2 = arbol.add("C", raiz);
		assertEquals(dg.calculate(arbol), 2);
		Position<String> nodo3 = arbol.add("D", nodo1);
		assertEquals(dg.calculate(arbol), 2);
		Position<String> nodo4 = arbol.add("E", nodo2);
		assertEquals(dg.calculate(arbol), 2);
		Position<String> nodo5 = arbol.add("F", nodo2);
		assertEquals(dg.calculate(arbol), 2);
		Position<String> nodo6 = arbol.add("G", nodo2);
		assertEquals(dg.calculate(arbol), 3);
		Position<String> nodo7 = arbol.add("H", nodo5);
		assertEquals(dg.calculate(arbol), 3);
		Position<String> nodo8 = arbol.add("I", nodo5);
		assertEquals(dg.calculate(arbol), 3);
		arbol.remove(nodo5);
		assertEquals(dg.calculate(arbol), 2);
		arbol.remove(nodo2);
		assertEquals(dg.calculate(arbol), 1);
	}
}