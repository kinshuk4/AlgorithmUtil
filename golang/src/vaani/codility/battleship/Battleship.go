package battleship

import (
	//"fmt"
	//"container/list"
	"strings"
	"strconv"
)

type LinesOfText [][]byte     // A slice of byte slices.

func Build(dimension int) [][]byte {
	board := make([][]byte, dimension)// [dimension][dimension]byte

	for i := 0; i < dimension; i++ {
		board[i] = make([]byte, dimension)
		for j := 0; j < dimension; j++ {
			board[i][j] = 'W';
		}
		//return board
	}
	return board
}

func parseCoordinates(coordinate string) []byte {
	coords := make([]byte, 2)
	coords[0] = coordinate[0] - 49
	coords[1] = coordinate[1] - 65
	//coords[0] = coords[0] - 1
	//fmt.Println(coords)

	return coords;
}

func markShipCoordinates(coordinates string, board [][]byte) {
	tokenizer := strings.Split(coordinates, " ")
	startCoord := tokenizer[0]
	endCoord := tokenizer[1]

	startCoords := parseCoordinates(startCoord);
	startRow := startCoords[0];
	startCol := startCoords[1];

	endCoords := parseCoordinates(endCoord);
	endRow := endCoords[0];
	endCol := endCoords[1];

	for i := startRow; i <= endRow; i++ {
		for j := startCol; j <= endCol; j++ {
			board[i][j] = 'S';
		}
	}
}

func markHit(hit string, board[][] byte) {
	coords := parseCoordinates(hit);
	board[coords[0]][coords[1]] = 'H';
}
func Solution(N int, S string, T string) string {
	sunkShips := 0;
	totalHits := 0;

	// Builds and initializes the board
	board := Build(N);

	//fmt.Println(sunkShips)
	//fmt.Println(totalHits)
	//fmt.Println(board)



	coordinates := strings.Split(S, ",")

	for i := 0; i < len(coordinates); i++ {
		markShipCoordinates(coordinates[i], board);
	}
	//fmt.Println(coordinates)

	hitToken := strings.Split(T, " ")
	//fmt.Println(hitToken)
	for i := 0; i < len(hitToken); i++ {
		hit := hitToken[i]
		//parseCoordinates(hit)
		markHit(hit, board);
	}
	//fmt.Println(board)


	//	// Count hits and sunken ships
	for i := 0; i < len(coordinates); i++ {
		ship := coordinates[i]
		shipTokenizer := strings.Split(ship, " ")
		startCoord := shipTokenizer[0]
		endCoord := shipTokenizer[1]

		startCoords := parseCoordinates(startCoord)
		startRow := startCoords[0]
		startCol := startCoords[1]

		endCoords := parseCoordinates(endCoord)
		endRow := endCoords[0]
		endCol := endCoords[1]
		shipHits := 0
		intactShipParts := 0

		for i := startRow; i <= endRow; i++ {
			for j := startCol; j <= endCol; j++ {
				if (board[i][j] == 'S') {
					intactShipParts++;
				} else if (board[i][j] == 'H') {
					shipHits++;
				}
			}
		}

		if (shipHits > 0) {
			if (intactShipParts == 0) {
				sunkShips++;
			} else {
				totalHits += shipHits;
			}
		}
	}
	return strconv.Itoa(sunkShips) + "," + strconv.Itoa(totalHits)
}
