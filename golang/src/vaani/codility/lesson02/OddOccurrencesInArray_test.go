package lesson02_test

import (
	"testing"

	"github.com/spring1843/go-codility/lesson02"
)

func TestBinaryGap(t *testing.T) {
	actual := lesson02.OddOccurrencesInArray([]int{9, 3, 9, 3, 9, 7, 9})
	expected := 7
	if actual != expected {
		t.Errorf("OddOccurrencesInArray failed, expected %d, got %d", expected, actual)
	}

	actual = lesson02.OddOccurrencesInArray([]int{8, 1, 5, 1, 8})
	expected = 5
	if actual != expected {
		t.Errorf("OddOccurrencesInArray failed, expected %d, got %d", expected, actual)
	}
}
