package liftstop_test

import (
	"testing"
	"fmt"
	"vaani/codility/liftstop"
)

func TestBinaryGap(t *testing.T) {
	A := [] int{60,80,40}
	B := [] int{2,3,5}

	actual := liftstop.Solution(A,B,5,2,200)
	fmt.Println(actual)
	//actual := lesson01.BinaryGap(1041)
	//expected := 5
	//if actual != expected {
	//	t.Errorf("BinaryGap failed, expected %d, got %d", expected, actual)
	//}
	//
	//actual = lesson01.BinaryGap(8)
	//expected = 0
	//if actual != expected {
	//	t.Errorf("BinaryGap failed, expected %d, got %d", expected, actual)
	//}
	//
	//actual = lesson01.BinaryGap(9)
	//expected = 2
	//if actual != expected {
	//	t.Errorf("BinaryGap failed, expected %d, got %d", expected, actual)
	//}
}
