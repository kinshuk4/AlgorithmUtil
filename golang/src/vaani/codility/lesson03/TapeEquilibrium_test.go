package lesson03_test

import (
	"testing"

	"github.com/spring1843/go-codility/lesson03"
)

func TestTapeEquilibrium(t *testing.T) {
	actual := lesson03.TapeEquilibrium([]int{3, 1, 2, 4, 3})
	expected := 1

	if actual != expected {
		t.Errorf("TapeEquilibrium failed, expected %d, got %d", expected, actual)
	}

	actual = lesson03.TapeEquilibrium([]int{3, 1})
	expected = 2
	if actual != expected {
		t.Errorf("TapeEquilibrium failed, expected %d, got %d", expected, actual)
	}

}
