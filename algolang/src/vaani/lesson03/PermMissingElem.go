package lesson03

// Original Problem
// https://codility.com/programmers/task/perm_missing_elem/
func PermMissingElem(A []int) int {
	sum := 0
	for _, element := range A {
		sum += element
	}
	expectedSum := (len(A) + 1) * (len(A) + 2) / 2
	return expectedSum - sum
}
