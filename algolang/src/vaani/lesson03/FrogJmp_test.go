package lesson03_test

import (
	"testing"

	"github.com/spring1843/go-codility/lesson03"
)

func TestFrogJmp(t *testing.T) {
	actual := lesson03.FrogJmp(10, 85, 30)
	expected := 3
	if actual != expected {
		t.Errorf("FrogJmp failed, expected %d, got %d", expected, actual)
	}

	actual = lesson03.FrogJmp(10, 41, 30)
	expected = 2
	if actual != expected {
		t.Errorf("FrogJmp failed, expected %d, got %d", expected, actual)
	}

	actual = lesson03.FrogJmp(4, 1, 1)
	expected = 0
	if actual != expected {
		t.Errorf("FrogJmp failed, expected %d, got %d", expected, actual)
	}

	actual = lesson03.FrogJmp(10, 20, 10)
	expected = 1
	if actual != expected {
		t.Errorf("FrogJmp failed, expected %d, got %d", expected, actual)
	}
}
