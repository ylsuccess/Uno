//@author: Yixin Liang 

public class CardList {
	
	private Card first;
	private int count;

	public int size() {
		return count;
	}
	
	public Card getFirst() {
		return this.first;
	}
	
	
	public CardList(boolean all) {
		first=null;
		count=0;
		if (all) {
			for (Card.Colors color : Card.Colors.values()) {
				for (int number=0;number<=9;number++) {
					add(number,color);
				}	
			}
		}
	}

	public void traverse() {
		Card current=first;
		while(current!=null) {
			System.out.println(current.toString());
			current=current.getNext();
		}
		
	}

	private void add(int number,Card.Colors color) {
		first = new Card(number, color, first);
		count++;
	}

	
	private void add(Card card) {
	
		card.setNext(first);
		first= card;
		count++;
	}

	private int countCards() {
		count = 0;
		Card temp= this.first;
		while(temp!=null) {
			this.count++;
			temp=temp.getNext();
		}
		
		return count; // you must remove this instruction
	}

	
	public void concatenateWith(CardList list) {
	
		if(this.first==null) 
		{
			this.first=list.getFirst();
		}
		else {
			Card t =first;
			
			while(t.getNext()!=null) 
			{
				t=t.getNext();
			}
			t.setNext(list.getFirst());
		}
		this.count = this.count + list.size();
		list=null;
	
	}

	/* DO THIS:
	 * moveTo: move the front card from this to the front of destination
	 */
	public void moveTo(CardList destination) {
	
		Card temp=this.first;
		this.first=this.first.getNext();
		temp.setNext(destination.first);
		destination.first=temp;
		destination.count = destination.count +1;
		this.count = this.count -1;
		}

		
	public void moveTo(int num,CardList destination) {
		
		int n = num;
		while(n>0)
		{
			this.moveTo(destination);
			n--;
		}
		
	}        
		
	
	public boolean moveTo(Card x,CardList destination) {
	
		
		
		Card temp = first;
		if(temp == x)
	   {
			this.moveTo(destination);
			return true;
		}
		else
		{
			while(temp.getNext() != x && temp.getNext()!= null)
			{
				temp = temp.getNext();
			}
			if(temp.getNext()== null)
				return false;
			else
			{
				Card current = temp.getNext();
				temp.setNext(temp.getNext().getNext());
				destination.add(current);
				this.count = this.count -1;
				return true;
			}
	}
	}	
	
	
	public void shuffle(int split) {
		
		
		  CardList L1 =new CardList(false);
		  CardList L2 =new CardList(false);
		 
		  int s = split;
		  while (s>0){
			  if(s%2==0) 
			  {
				  this.moveTo(L1);
			  }
			  else 
			  {
				  this.moveTo(L2);
			  }
			  s--;
		  }
		   L1.concatenateWith(L2);
		   this.concatenateWith(L1); 
	}
		  
		
		  
	public Card search(Card x) {
	Card temp=first;
		while(temp!=null && !temp.matches(false,x)) 
		{
			temp=temp.getNext();
		}
		if(temp==null)
		{
			return null;
		}
		else
		{
			return temp;
		}

	}

	
	public Card getCard(Card card) {
		Card tem=this.getFirst();
		while(!(tem.matches(true,card)) && tem!=null) {
			tem=tem.getNext();
		}
		if(tem==null)
		{return null;}
		else {
			return tem;
		}

	}

}
