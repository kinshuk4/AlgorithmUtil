package lesson03

// Original Problem
// https://codility.com/programmers/task/frog_jmp/
func FrogJmp(X int, Y int, D int) int {
	if X >= Y {
		return 0
	}

	distance := Y - X
	minJumps := distance / D
	if (minJumps*D)+X >= Y {
		return minJumps
	}
	return minJumps + 1
}
