package lesson03

import (
	"math"
)

// Original Problem
// https://codility.com/programmers/task/tape_equilibrium/
func TapeEquilibrium(A []int) int {
	if len(A) == 2 {
		return int(math.Abs(float64(A[0] - A[1])))
	}

	var mindiff float64 = 1001
	N := len(A)
	P, headSum, tailSum := 0, 0, 0

	for _, v := range A {
		tailSum += v
	}

	for P < N-1 {
		headSum += A[P]
		tailSum -= A[P]
		diff := math.Abs(float64(tailSum - headSum))
		if diff < mindiff {
			mindiff = diff
		}
		P++
	}

	return int(mindiff)
}
