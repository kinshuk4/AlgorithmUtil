package lesson05_test

import (
	"testing"

	"github.com/spring1843/go-codility/lesson05"
)

func TestPassingCars(t *testing.T) {
	actual := lesson05.PassingCars([]int{0, 1, 0, 1, 1})
	expected := 5
	if actual != expected {
		t.Errorf("PermMissingElm failed, expected %d, got %d", expected, actual)
	}

	actual = lesson05.PassingCars([]int{0, 1, 0, 1, 1, 1})
	expected = 7
	if actual != expected {
		t.Errorf("PermMissingElm failed, expected %d, got %d", expected, actual)
	}
}

// This test is disabled because it takes too long
func DisabledForPerformanceTestOneBillionPassingCars(t *testing.T) {
	var cars []int
	i := 0
	for i < 2000000000 {
		if i%2 == 0 {
			cars = append(cars, 1)
		} else {
			cars = append(cars, 0)
		}

		i++
	}

	actual := lesson05.PassingCars(cars)
	expected := -1
	if actual != expected {
		t.Errorf("PermMissingElm failed, expected %d, got %d", expected, actual)
	}
}
