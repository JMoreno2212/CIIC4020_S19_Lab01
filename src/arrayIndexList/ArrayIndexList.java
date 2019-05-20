package arrayIndexList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import indexList.IndexList;

public class ArrayIndexList<E> implements IndexList<E> {
	private static final int INITCAP = 1; 
	private static final int CAPTOAR = 1; 
	private static final int MAXEMPTYPOS = 2; 
	private E[] element; 
	private int size; 

	@SuppressWarnings("unchecked")
	public ArrayIndexList() { 
		element = (E[]) new Object[INITCAP]; 
		size = 0; 
	} 
	

	public void add(int index, E e) throws IndexOutOfBoundsException {
		if(index > size || index < 0) {throw new IndexOutOfBoundsException("add: Invalid index = " + index );}
		if(size == element.length) {changeCapacity(CAPTOAR);}
		if(index < size) {moveDataOnePositionTR(index, size - 1);}
		element[index] = e;
		size++;
	}


	public void add(E e) {
		if(size == element.length) {changeCapacity(CAPTOAR);}
		element[size] = e;
		size++;
	}


	public E get(int index) throws IndexOutOfBoundsException {
		if(index > size - 1 || index < 0) {throw new IndexOutOfBoundsException("get: Invalid index = " + index );}
		return element[index]; 
	}


	public boolean isEmpty() {
		return size == 0;
	}


	public E remove(int index) throws IndexOutOfBoundsException {
		if(index > size - 1 || index < 0) {throw new IndexOutOfBoundsException("remove: Invalid index = " + index );}
		E old = element[index];
		if(index < size) {moveDataOnePositionTL(index + 1, size - 1);}
		element[size - 1] = null;
		size--;
		if(element.length - size > MAXEMPTYPOS) {changeCapacity(-CAPTOAR);}
		return old;
	}


	public E set(int index, E e) throws IndexOutOfBoundsException {
		if(index > size - 1 || index < 0) {throw new IndexOutOfBoundsException("set: Invalid index = " + index );}
		E old = element[index];
		element[index] = e;
		return old;
	}


	public int size() {
		return size;
	}	
	
	public int capacity() {
		return element.length;
	}
	
	// private methods  -- YOU CAN NOT MODIFY ANY OF THE FOLLOWING
	// ... ANALYZE AND USE WHEN NEEDED
	
	// you should be able to decide when and how to use
	// following method.... BUT NEED TO USE THEM WHENEVER
	// NEEDED ---- THIS WILL BE TAKEN INTO CONSIDERATION WHEN GRADING
	
	private void changeCapacity(int change) { 
		int newCapacity = element.length + change; 
		@SuppressWarnings("unchecked")
		E[] newElement = (E[]) new Object[newCapacity]; 
		for (int i=0; i<size; i++) { 
			newElement[i] = element[i]; 
			element[i] = null; 
		} 
		element = newElement; 
	}
	
	// useful when adding a new element with the add
	// with two parameters....
	private void moveDataOnePositionTR(int low, int sup) { 
		// pre: 0 <= low <= sup < (element.length - 1)
		for (int pos = sup; pos >= low; pos--)
			element[pos+1] = element[pos]; 
	}

	// useful when removing an element from the list...
	private void moveDataOnePositionTL(int low, int sup) { 
		// pre: 0 < low <= sup <= (element.length - 1)
		for (int pos = low; pos <= sup; pos++)
			element[pos-1] = element[pos]; 
	}


	// The following two methods are to be implemented as part of an exercise
	public Object[] toArray() {
		Object[] result = new Object[size];
		for(int i = 0; i < size; i++) {
			result[i] = element[i];
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	public <T1> T1[] toArray(T1[] array) {
		if(array.length < size) {array = Arrays.copyOf(array, size);}
		for(int i = 0; i < array.length; i++) {
			if(i < size) {array[i] = (T1) element[i];}
			else array[i] = null;
		}
		return array;
	}
	
	public void clear() {
		for(int i = 0; i < element.length; i++) {
			element[i] = null;
		}
		size = 0;
	}
	
	public Object clone() {
		Object[] result = new Object[capacity()];
		for(int i = 0; i < element.length; i++) {
			result[i] = element[i];
		}
		return result;
	}
	
	public List<E> subList(int fromIndex, int toIndex){
		List<E> result = new ArrayList<>();
		for(int i = fromIndex; i <= toIndex; i++) {
			result.add(element[i]);
		}
		
		return result;
	}
	
}
