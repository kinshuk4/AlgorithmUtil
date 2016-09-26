package liftstop

func Solution(A []int, B []int, M int, X int, Y int) int {
	// write your code in Java SE 8
	paxInLiftWeight := 0;
	paxInLiftCount := 0;
	requestedFloors := make(map[int]int)
	stops := 0;
	for i := 0; i < len(A); i++ {
		paxWeight := A[i];
		if (paxInLiftCount >= X || paxInLiftWeight + paxWeight > Y) {
			stops += len(requestedFloors) + 1;
			requestedFloors = make(map[int]int)
			//requestedFloors.clear();
			paxInLiftCount = 0;
			paxInLiftWeight = 0;
		}
		paxInLiftCount = paxInLiftCount+1;
		paxInLiftWeight += paxWeight;
		requestedFloors[B[i]] = B[i];
	}
	if len(requestedFloors) != 0 {
		stops += len(requestedFloors) + 1;
	}
	return stops;
}
