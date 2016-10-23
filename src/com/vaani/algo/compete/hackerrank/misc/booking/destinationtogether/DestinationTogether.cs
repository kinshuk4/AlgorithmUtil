class Booking
	{
		static void Main(String[] args)
		{
			String nstring = Console.ReadLine();
			String [] narray = nstring.Split(new char[] {' '}).ToArray();
			
			if(narray.Length==3)
			{
			
				int m = Convert.ToInt32(narray[0]);
				int n = Convert.ToInt32(narray[1]);
				int c = Convert.ToInt32(narray[2]);
			if((m>0 && m<11) && (n>0 && n<11))
			{
				if(c<=m && c<=n)
				{
					int cm = m-c;
					int cn = n-c;
					
					int comb = cm + cn + c;
					int res=1;
					
					for(int i=comb-1;i>0;i--)
					{
						res = res * i;
					}
					
					Console.WriteLine(res);
					Console.ReadKey();
				}
			}
			
			}
			
		}