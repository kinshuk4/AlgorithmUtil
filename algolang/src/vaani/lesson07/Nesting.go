package lesson07

//Original Problem:
//https://codility.com/programmers/task/nesting/
func Nesting(S string) int {

	if len(S) == 0 {
		return 1
	}

	if len(S) == 1 {
		return 0
	}

	stack := Stack{Stack: ``}
	deQueuedElement := ``

	i := 0
	for i < len(S) {
		currentChar := S[i : i+1]
		if currentChar == `(` {
			stack.push(currentChar)
		}

		if currentChar == `)` {
			deQueuedElement = stack.pop()
			if deQueuedElement == `` {
				return 0
			}
		}
		i++
	}

	if stack.isEmpty() == true {
		return 1
	}
	return 0
}

type Stack struct {
	Stack string
}

func (q *Stack) push(char string) {
	q.Stack += char
}

func (q *Stack) pop() string {
	if q.isEmpty() == true {
		return ``
	}

	element := q.Stack[len(q.Stack)-1:]
	q.Stack = q.Stack[:len(q.Stack)-1]
	return element
}

func (q *Stack) size() int {
	return len(q.Stack)
}

func (q *Stack) isEmpty() bool {
	if q.size() == 0 {
		return true
	}
	return false
}
