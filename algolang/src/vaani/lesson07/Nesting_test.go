package lesson07_test

import (
	"testing"

	"github.com/spring1843/go-codility/lesson07"
)

func TestNesting(t *testing.T) {
	actual := lesson07.Nesting(`(()(())())`)
	expected := 1
	if actual != expected {
		t.Errorf("Nesting failed, expected %d, got %d", expected, actual)
	}

	actual = lesson07.Nesting(``)
	expected = 1
	if actual != 1 {
		t.Error("missmatch for 2 case, expected 1 , got %d", actual)
	}

	actual = lesson07.Nesting(`(`)
	expected = 0
	if actual != 0 {
		t.Error("missmatch for 3 case, expected 0 , got %d", actual)
	}

	actual = lesson07.Nesting(`((()))`)
	expected = 1
	if actual != 1 {
		t.Error("missmatch for 4 case, expected 1 , got %d", actual)
	}

	actual = lesson07.Nesting(`((()))(`)
	expected = 0
	if actual != 0 {
		t.Error("missmatch for 5 case, expected 0 , got %d", actual)
	}

	actual = lesson07.Nesting(`())`)
	expected = 0
	if actual != 0 {
		t.Error("missmatch for 6 case, expected 0 , got %d", actual)
	}

	actual = lesson07.Nesting(`))`)
	expected = 0
	if actual != 0 {
		t.Error("missmatch for 7 case, expected 0 , got %d", actual)
	}
}
