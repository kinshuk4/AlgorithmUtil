package lesson15_test

import (
	"testing"

	"github.com/spring1843/go-codility/lesson15"
)

func TestAbsDistinct(t *testing.T) {
	actual := lesson15.AbsDistinct([]int{-5, -3, -1, 0, 3, 6})
	expected := 5
	if actual != expected {
		t.Errorf("AbsDistinct failed, expected %d, got %d", expected, actual)
	}
}
