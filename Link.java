/**
 * 
 */

/**
 * @author Gary
 *
 */
public class Link {
public String strPathType;
public int iX;
public int iY;
//References next link in linked list
public Link next;

public Link(int iX, int iY, String strPathType){
	this.iX = iX;
	this.iY = iY;
	this.strPathType = strPathType;
}
//no arg constructor
public Link(){

}

public void display(){
	System.out.println(iX + " & " + iY + " path type: " + strPathType);
}
}
