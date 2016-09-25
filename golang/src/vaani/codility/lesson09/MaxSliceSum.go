package lesson09

//Original Problem:
//https://codility.com/programmers/task/max_slice_sum/
func MaxSliceSum(A []int) int {
	if len(A) == 0 {
		return -1
	}

	maxSlice := -1000000
	maxEnding := 0

	for _, value := range A {
		maxEnding = Max(value, maxEnding+value)
		maxSlice = Max(maxSlice, maxEnding)
	}

	return maxSlice
}

func Max(x int, y int) int {
	if x > y {
		return x
	}
	return y
}
