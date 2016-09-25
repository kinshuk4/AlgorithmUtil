package lesson15

//Original Problem
//https://codility.com/programmers/task/abs_distinct/
func AbsDistinct(A []int) int {
	result, start, startValue, endValue, curValue := 0, 0, 0, 0, 0
	end := len(A) - 1

	for start <= end {
		result++
		startValue = Abs(A[start])
		endValue = Abs(A[end])
		curValue = Max(startValue, endValue)
		for start <= end && Abs(A[start]) == curValue {
			start++
		}
		for start <= end && Abs(A[end]) == curValue {
			end--
		}
	}

	return result
}

func Max(x int, y int) int {
	if x < y {
		return y
	}
	return x
}

func Abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
