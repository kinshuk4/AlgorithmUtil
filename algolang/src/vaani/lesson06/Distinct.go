package lesson06

//Original Problem:
//https://codility.com/programmers/task/distinct/
func Distinct(A []int) int {
	if len(A) == 1 {
		return 1
	}

	if len(A) == 0 {
		return 0
	}

	sortedA := sort(A)

	lastItem := sortedA[0]
	uniques := 1

	i := 1
	for i < len(sortedA) {
		if lastItem != sortedA[i] {
			uniques++
		}

		lastItem = sortedA[i]
		i++
	}
	return uniques
}

func sort(A []int) []int {
	if len(A) <= 1 {
		return A
	}

	left, right := split(A)
	left = sort(left)
	right = sort(right)
	return merge(left, right)
}

func split(A []int) ([]int, []int) {
	return A[0 : len(A)/2], A[len(A)/2:]
}

func merge(A, B []int) []int {
	arr := make([]int, len(A)+len(B))

	j, k := 0, 0

	for i := 0; i < len(arr); i++ {
		if j >= len(A) {
			arr[i] = B[k]
			k++
			continue
		} else if k >= len(B) {
			arr[i] = A[j]
			j++
			continue
		}
		if A[j] > B[k] {
			arr[i] = B[k]
			k++
		} else {
			arr[i] = A[j]
			j++
		}
	}

	return arr
}
