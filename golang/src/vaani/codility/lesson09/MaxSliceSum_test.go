package lesson09_test

import (
	"testing"

	"github.com/spring1843/go-codility/lesson09"
)

func TestMaxSliceSum(t *testing.T) {
	actual := lesson09.MaxSliceSum([]int{3, 2, -6, 4, 0})
	expected := 5
	if actual != expected {
		t.Errorf("MaxSliceSum failed, expected %d, got %d", expected, actual)
	}

	actual = lesson09.MaxSliceSum([]int{})
	expected = -1
	if actual != expected {
		t.Errorf("MaxSliceSum failed, expected %d, got %d", expected, actual)
	}

}
