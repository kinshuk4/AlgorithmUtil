package lesson03_test

import (
	"reflect"
	"testing"

	"github.com/spring1843/go-codility/lesson03"
)

func TestPermMissingElem(t *testing.T) {
	actual := lesson03.PermMissingElem([]int{2, 3, 1, 5})
	expected := 4

	if reflect.DeepEqual(actual, expected) != true {
		t.Errorf("PermMissingElm failed, expected %d, got %d", expected, actual)
	}

	actual = lesson03.PermMissingElem([]int{})
	expected = 1
	if reflect.DeepEqual(actual, expected) != true {
		t.Errorf("PermMissingElm failed, expected %d, got %d", expected, actual)
	}

	actual = lesson03.PermMissingElem([]int{2})
	expected = 1
	if reflect.DeepEqual(actual, expected) != true {
		t.Errorf("PermMissingElm failed, expected %d, got %d", expected, actual)
	}
}
