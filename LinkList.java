/**
 * 
 */

/**
 * @author Gary
 *
 */
public class LinkList {
	public Link fLink;
	int counter= 0;
	
	//Checks if the linkList is empty
	public boolean isEmpty(){
		return(fLink == null);
	}
	//Adds new link to linked list
	public void insertFLink(int iX, int iY, String strPathType){
		//Connects fLink to nLink
		Link nLink = new Link(iX, iY, strPathType);
		nLink.next = fLink;
		fLink = nLink;
		System.out.println("adding: y: " + fLink.iY + " x: " + fLink.iX + " " + strPathType + " to linkedlist.");
	}
	//Finds theLink with iX and iY coordinates that is passed to the method
	public Link find(int iX, int iY){
		Link theLink = fLink;
		if(!isEmpty()){
			System.out.println("Searching for x:" + iX + " y: " + iY);
			while(theLink.iX != iX || theLink.iY != iY){
				//if the link is null return null since there are no more links in the list
				if (theLink.next ==null){
					System.out.println("Link could not be found.. returning null");
					return null;
				}else{
					//move to the next link in the list
					theLink = theLink.next;
				}
			}
		}else{
			System.out.println("Linked list is empty");
		}
		System.out.println("Find method: " + " passed x: " + iX + " y: " + iY + ". Returned x: " + theLink.iX 
							+ " y: " + theLink.iY);
		return theLink;
	}
 
	//removes specified link
	public Link removeLink(int iX, int iY){
		Link cLink = fLink;
		Link pLink = fLink;
		while(cLink.iY != iY || cLink.iX != iX){
			if (cLink.next == null){
				//x and y coords were not found returning null
				return null;
			}else{
				//sets the current link as the prev link
				pLink = cLink;
				//sets the current link as the next link to continue looking for match
				System.out.println("cLink x: " + cLink.iX + " y: " + cLink.iY + " does not match x: " +
									iX + " y: " + iY);
				cLink = cLink.next;
				System.out.println("Now checking cLink x: " + cLink.iX + " y: " + iY);
			}		
		}
		System.out.println("cLink: " + cLink.iX + " & " + cLink.iY + " should match " + iX + " & " + iY);
		//once a link is found it will check if the cLink is the fLink
		if (cLink == fLink){
			System.out.println("First link in linked list removed x: " + fLink.iX + " y: " + fLink.iY);
			//sets fLink as the next link deleting the very first link
			fLink = fLink.next;
		}else{
			//sets the prev link as the next link and cLink as next deleting the current link
			System.out.println("What was removed x: " + cLink.iX + " y: " + cLink.iY);
			pLink.next = cLink.next;
		}
		//returns the deleted link
		return cLink;
	}
	
	//display linked list
	public void Display(){
		Link theLink = fLink;
		int counter = 1;
		while(theLink != null){
			theLink.display();
			System.out.println("Next link: " + theLink.next + "\n");
			System.out.println(counter++);
			theLink = theLink.next;
		}
	}
	
}
