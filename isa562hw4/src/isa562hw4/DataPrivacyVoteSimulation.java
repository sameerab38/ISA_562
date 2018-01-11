package isa562hw4;

/**
 * @author Sameera Bammidi
 * Created On: 12/02/2017
 *
 */

public class DataPrivacyVoteSimulation
{

	private static boolean flipAcoin(double Head_bias)
	{ // param is probability of coin turning up heads

		if( Math.random() < Head_bias)
		{
			return true; // heads
		}
		else 
			return false; // tails
	}

	public static void main(String args[])
	{
		double prOfTrump = 0.62; 
		int n= 10000; // total all votes
		int n_trumpvotes = (int) (prOfTrump*10000); // no majority, even in simulation 
		// if set to 6000, it will be a clear true majority, (atleast in simulation) 

		int[] reported = new int[10000] ; // will fill this reported votes, with -1 for hill and +1 for trump

		double r1_bias = 0.25;
		double r2_bias = 0.5;


		for(int i=0;i<n;i++)
		{
			if(i<n_trumpvotes)
			{ // true vote is Trump

				int truevote = 1; 

				// r1 flip a coin 
				if( flipAcoin(r1_bias) ) reported[i] = truevote;
				else
				{
					//r2 flip another coin
					if( flipAcoin(r2_bias)==true)
					{
						reported[i] = 1;
					}
					else
					{
						reported[i] = -1;
					}
				}				
			}
			else
			{	// true vote is Hillary

				int truevote = -1; 

				// r1 flip a coin 
				if( flipAcoin(r1_bias) ) reported[i] = truevote;
				else
				{
					//r2 flip another coin
					if( flipAcoin(r2_bias)==true)
					{
						reported[i] = 1;
					}
					else
					{
						reported[i] = -1;
					}
				}
			}			
		}

		double treps=0;
		double hreps=0 ;
		for(int i=0;  i<10000 ; i++)
		{
			if(reported[i]==1) treps++;
			else 
				hreps++;
		}
		System.out.println("Pr[Trump] : "+ prOfTrump);
		System.out.println("Number that reported Trump: "+ treps);
		System.out.println("Number that reported Hillary: "+ hreps);

		double true_trumpvotes_reported = 0;
		for(int i = 0 ; i < n_trumpvotes ; i++)
		{
			if(reported[i]==1)
			{
				true_trumpvotes_reported++;
			}
		}

		System.out.println("from simulation,  user privacy: P[Trump | ~reported as T] =" + (true_trumpvotes_reported /treps));
		/*double true_hillaryvotes_reported = 0;
		for(int i = n_trumpvotes ; i < n ; i++)
		{
			if(reported[i]==-1)
			{
				true_hillaryvotes_reported++;
			}
		}

		System.out.println("from simulation,  user privacy: P[Hillary | ~reported as H] =" + (true_hillaryvotes_reported /hreps));*/

	}

}
