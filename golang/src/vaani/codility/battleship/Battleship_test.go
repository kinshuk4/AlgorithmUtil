package battleship_test

import (
	"testing"

	"vaani/codility/lesson01"
)

func TestBinaryGap(t *testing.T) {
	actual := lesson01.BinaryGap(1041)
	expected := 5
	if actual != expected {
		t.Errorf("BinaryGap failed, expected %d, got %d", expected, actual)
	}

	actual = lesson01.BinaryGap(8)
	expected = 0
	if actual != expected {
		t.Errorf("BinaryGap failed, expected %d, got %d", expected, actual)
	}

	actual = lesson01.BinaryGap(9)
	expected = 2
	if actual != expected {
		t.Errorf("BinaryGap failed, expected %d, got %d", expected, actual)
	}
}

