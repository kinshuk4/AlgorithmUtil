package lesson06_test

import (
	"testing"

	"github.com/spring1843/go-codility/lesson06"
)

func TestSolution(t *testing.T) {
	actual := lesson06.Distinct([]int{2, 1, 1, 2, 3, 1})
	expected := 3
	if actual != expected {
		t.Errorf("Distinct failed, expected %d, got %d", expected, actual)
	}

	actual = lesson06.Distinct([]int{})
	expected = 0
	if actual != expected {
		t.Errorf("Distinct failed, expected %d, got %d", expected, actual)
	}

	actual = lesson06.Distinct([]int{2, 1})
	expected = 2
	if actual != expected {
		t.Errorf("Distinct failed, expected %d, got %d", expected, actual)
	}

	actual = lesson06.Distinct([]int{2})
	expected = 1
	if actual != expected {
		t.Errorf("Distinct failed, expected %d, got %d", expected, actual)
	}

	actual = lesson06.Distinct([]int{-1, 2, -1, 3})
	expected = 3
	if actual != expected {
		t.Errorf("Distinct failed, expected %d, got %d", expected, actual)
	}
}
