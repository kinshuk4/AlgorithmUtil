package lesson01

//Original Problem
//https://codility.com/programmers/task/binary_gap/
func BinaryGap(N int) int {
	divisor, remainder := -1, -1
	maxGap, currentGap := 0, 0
	number := N
	surroundedBy1 := false

	for divisor != 0 {
		divisor = number / 2
		remainder = number % 2
		number = divisor
		if remainder == 1 {
			surroundedBy1 = true
			if currentGap > maxGap {
				maxGap = currentGap
			}
			currentGap = 0
		} else {
			if surroundedBy1 == true {
				currentGap++
			}
		}
	}

	return maxGap
}
