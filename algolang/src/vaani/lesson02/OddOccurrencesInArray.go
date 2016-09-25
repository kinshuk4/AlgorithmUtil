package lesson02

//Original Problem:
// https://codility.com/programmers/task/odd_occurrences_in_array/
func OddOccurrencesInArray(A []int) int {
	missing := 0
	for _, v := range A {
		missing ^= v
	}
	return missing
}
