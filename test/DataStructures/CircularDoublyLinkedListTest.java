/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.InvalidArgumentException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ITSC 2214 Q
 * @version 1.0
 */
public class CircularDoublyLinkedListTest {
    
    public CircularDoublyLinkedListTest() {
    }

    /**
     * Test of first method, of class CircularDoublyLinkedList.
     */
    @Test(expected=EmptyCollectionException.class)
    public void testFirst1() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.first();
    }
    
    /**
     * Test of first method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testFirst2() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.addFirst(10);
        assertEquals(circle.first().intValue(), 10);
    }
    
    /**
     * Test of current method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testCurrent() {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        assertNull(circle.current());
        
        circle.addFirst(10);
        assertEquals(circle.current().intValue(), 10);
    }

    /**
     * Test of last method, of class CircularDoublyLinkedList.
     */
    @Test(expected=EmptyCollectionException.class)
    public void testLast() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.addLast(10);
        assertEquals(circle.last().intValue(), 10);
        circle.removeLast();
        circle.last();
    }

    /**
     * Test of next method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testNext() {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.next();
        
        circle.addFirst(10);
        circle.addLast(17);
        circle.next();
        assertEquals(circle.current().intValue(), 17);
        assertEquals(circle.size(), 2);
        try{
            assertEquals(circle.getNode(1).getElement().intValue(), 10);
        } 
        catch (Exception ex){
            fail("testNext");
        }
    }

    /**
     * Test of isEmpty method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testIsEmpty() {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        assertTrue(circle.isEmpty());
        circle.addFirst(10);
        assertFalse(circle.isEmpty());
    }

    /**
     * Test of printList method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testPrintList() {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.printList();
        circle.addFirst(10);
        circle.printList();
    }

    /**
     * TODO Test of addLast method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testAddLast() {
        //create the new node:
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        //Assert statements: add an element, check the size
        circle.addLast(10);
        assertEquals(circle.size(), 1);
        assertEquals(circle.current().intValue(), 10);
        //Add another element. check the size
        circle.addLast(17);
        assertEquals(circle.size(), 2);
        try {
            assertEquals(circle.getNode(0).getElement().intValue(), 10);
            assertEquals(circle.getNode(1).getElement().intValue(), 17);
        } 
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Test of addFirst method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testAddFirst() {
        //Create a new node:
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        //Add an element and check the size
        circle.addFirst(10);
        assertEquals(circle.size(), 1);
        assertEquals(circle.current().intValue(), 10);
        //Add another element, check the new size
        circle.addFirst(17);
        assertEquals(circle.size(), 2);
        try {
            assertEquals(circle.getNode(0).getElement().intValue(), 17);
            assertEquals(circle.getNode(1).getElement().intValue(), 10);
        } 
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Test of addAfter method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testAddAfter() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();

        circle.addFirst(10); 
        circle.addAfter(10, 17);
        assertEquals(circle.size(), 2);
        try {
            assertEquals(circle.getNode(0).getElement().intValue(), 10);
            assertEquals(circle.getNode(1).getElement().intValue(), 17);
        } 
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Test of remove method, of class CircularDoublyLinkedList.
     */
    @Test(expected=EmptyCollectionException.class)
    public void testRemove1() throws Exception {
        //TODO complete the Unit testing
        //of the EmptyCollectionException in Remove() method
        //Create a new node
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        //Just removing first
        circle.removeFirst();
        fail("no exception");
        

    }
    
    /**
     * Test of remove method, of class CircularDoublyLinkedList.
     */
    @Test(expected=ElementNotFoundException.class)
    public void testRemove2() throws Exception {
        //TODO complete the Unit testing
        //of the ElementNotFoundException in Remove() method
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        assertEquals(circle.size(), 0);
        circle.addFirst(1);
        circle.remove(10);
    }
    
    /**
     * Test of remove method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testRemove3() throws Exception {
        //TODO complete the Unit testing of
        //logic correctness of the remove() method
        //Create a new node
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        System.out.println("testRemove");
        //Declare test values
        Integer element = 3;
        Integer element2 = 4;
        Integer element3 = 5;
        Integer element4 = 6;
        //adding test values
        circle.addFirst(element);
        circle.addFirst(element2);
        circle.addFirst(element3);
        circle.addFirst(element4);
        System.out.println(circle.toString());
        //Testing the test values through try catch
        try {
            assertTrue(circle.remove(element).equals(element));
            assertTrue(circle.size() == 3);
            assertTrue(circle.first().equals(element4));
            assertTrue(circle.last().equals(element2));
            assertTrue(circle.remove(element4).equals(element4));
            assertTrue(circle.first().equals(element3));
            assertTrue(circle.last().equals(element2));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    /**
     * Test of removeFirst method, of class CircularDoublyLinkedList.
     */
    @Test(expected=EmptyCollectionException.class)
    public void testRemoveFirst1() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.removeFirst();
    }
    
    /**
     * Test of removeFirst method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testRemoveFirst2() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();

        circle.addFirst(10); 
        circle.addLast(17);
        circle.removeFirst();
        assertEquals(circle.size(), 1);
        try {
            assertEquals(circle.getNode(0).getElement().intValue(), 17);
            assertEquals(circle.current().intValue(), 17);
        } 
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Test of removeLast method, of class CircularDoublyLinkedList.
     */
    @Test(expected=EmptyCollectionException.class)
    public void testRemoveLast1() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.removeLast();
    }
    /**
     * Test of removeLast method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testRemoveLast2() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();

        circle.addFirst(10); 
        circle.addFirst(17);
        circle.removeLast();
        assertEquals(circle.size(), 1);
        try {
            assertEquals(circle.getNode(0).getElement().intValue(), 17);
            assertEquals(circle.current().intValue(), 17);
        } 
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Test of contains method, of class CircularDoublyLinkedList.
     */
    @Test(expected=EmptyCollectionException.class)
    public void testContains1() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.contains(17);
    }

        /**
     * Test of contains method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testContains2() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();

        circle.addFirst(10); 
        circle.addFirst(17);
        assertTrue(circle.contains(17));
        assertTrue(circle.contains(10));
        assertFalse(circle.contains(25));
    }
    
    /**
     * Test of find method, of class CircularDoublyLinkedList.
     */
    @Test(expected=EmptyCollectionException.class)
    public void testFind1() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.find(0);
    }
    
    /**
     * Test of find method, of class CircularDoublyLinkedList.
     */
    @Test(expected=ElementNotFoundException.class)
    public void testFind2() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.addFirst(10); 
        circle.addFirst(17);
        try {
            assertEquals(circle.find(new Integer(10)).getElement().intValue(), 10);
            assertEquals(circle.find(new Integer(17)).getElement().intValue(), 17);
        } 
        catch (Exception ex){
            ex.printStackTrace();
        }
        circle.find(new Integer(2));
    }    

    /**
     * Test of getNode method, of class CircularDoublyLinkedList.
     */
    @Test(expected=EmptyCollectionException.class)
    public void testGetNode1() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.getNode(0);
    }

    /**
     * Test of getNode method, of class CircularDoublyLinkedList.
     */
    @Test(expected=InvalidArgumentException.class)
    public void testGetNode2() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.addFirst(10); 
        circle.addFirst(17);
        circle.getNode(3);
    }
    
/**
     * Test of getNode method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testGetNode3() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.addFirst(10); 
        circle.addFirst(17);
        assertEquals(circle.getNode(0).getElement().intValue(),17);
        assertEquals(circle.getNode(1).getElement().intValue(),10);
    }
    
    /**
     * Test of get method, of class CircularDoublyLinkedList.
     */
    @Test(expected=EmptyCollectionException.class)
    public void testGet1() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.get(0);
    }

    /**
     * Test of get method, of class CircularDoublyLinkedList.
     */
    @Test(expected=InvalidArgumentException.class)
    public void testGet2() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.addFirst(10); 
        circle.addFirst(17);
        circle.get(3);
    }
    
    /**
     * Test of get method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testGet3() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.addFirst(10); 
        circle.addFirst(17);
        assertEquals(circle.get(0).intValue(),17);
        assertEquals(circle.get(1).intValue(),10);
    }

    /**
     * Test of set method, of class CircularDoublyLinkedList.
     */
    @Test(expected=EmptyCollectionException.class)
    public void testSet1() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.set(0, 66);
    }

    /**
     * Test of set method, of class CircularDoublyLinkedList.
     */
    @Test(expected=InvalidArgumentException.class)
    public void testSet2() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.addFirst(10); 
        circle.addFirst(17);
        circle.set(3, 55);
    }
    
    /**
     * Test of set method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testSet3() throws Exception {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        circle.addFirst(10); 
        circle.addLast(17);
        circle.addLast(18);
        circle.set(0, 55);
        assertEquals(circle.get(0).intValue(),55);
        assertEquals(circle.get(1).intValue(),17);
        assertEquals(circle.get(2).intValue(),18);
        assertEquals(circle.size(), 3);
        assertEquals(circle.current().intValue(),55);
        circle.set(1, 56);
        assertEquals(circle.get(0).intValue(),55);
        assertEquals(circle.get(1).intValue(),56);
        assertEquals(circle.get(2).intValue(),18);
        assertEquals(circle.size(), 3);
        assertEquals(circle.current().intValue(),55);
        circle.set(2, 57);
        assertEquals(circle.get(0).intValue(),55);
        assertEquals(circle.get(1).intValue(),56);
        assertEquals(circle.get(2).intValue(),57);
        assertEquals(circle.size(), 3);
        assertEquals(circle.current().intValue(),55);        
    }

    /**
     * Test of size method, of class CircularDoublyLinkedList.
     */
    @Test
    public void testSize() {
        CircularDoublyLinkedList<Integer> circle = new CircularDoublyLinkedList<Integer>();
        assertEquals(circle.size(), 0);
        circle.addFirst(10); 
        circle.addFirst(17);
        circle.addFirst(18);
        assertEquals(circle.size(), 3);
    }
    
}
