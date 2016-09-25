package lesson05

// Original Problem
// https://codility.com/programmers/task/passing_cars/
func PassingCars(A []int) int {
	N := len(A)
	i := 0
	sumZeros := 0
	passingCars := 0
	for i < N {
		if A[i] == 0 {
			sumZeros++
		}

		if A[i] == 1 {
			passingCars += sumZeros
		}

		i++
	}

	if passingCars > 1000000000 {
		return -1
	}

	return passingCars
}
