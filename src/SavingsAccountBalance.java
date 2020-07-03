public class SavingsAccountBalance {
	public static double balance(double openingSum, double interestRate, double taxFreeLimit, double taxRate,
			int numMonths) {
		if (numMonths > 0) {
			double monthlyInterest = interestForMonth(interestRate, openingSum);

			double monthlyTax = 0;
			{
				// In case anyone is wondering, I put these brackets here to put this into a
				// scope so that taxable gets removed from the stack since it's not needed
				// beyond this block
				double taxable = (openingSum - taxFreeLimit);
				if (taxable > 0) {
					monthlyTax = interestForMonth(taxRate, taxable);
				}
			}

			// Since java doesn't have tail-call optimization, this will take up a lot of
			// memory if the amount of months are too many
			return balance(monthlyInterest + openingSum - monthlyTax, interestRate, taxFreeLimit, taxRate,
					numMonths - 1);
		}
		return openingSum;
	}

	// Returns how much money a given interest rate would generate during a month
	public static double interestForMonth(double interest, double money) {
		return (((interest / 100) + 1) * money) - money;
	}
}
