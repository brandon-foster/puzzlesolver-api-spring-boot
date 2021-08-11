package com.joyldp;

import com.puzzlesolver.PuzzleSolver;
import com.puzzlesolver.PuzzleState;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@SpringBootApplication
public class LearningSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringApplication.class, args);
	}

	public class StepPath {
		private String path;
		public StepPath(String path) {
			this.path = path;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
	}

	@RestController
	@RequestMapping("/puzzlesolver")
	public class PuzzleSolverController {

		@GetMapping
		public StepPath solvePuzzle(@RequestParam(value="initial-state", required = false)String initialState) {
			String outputFlag = null;
			String queueFlag = null;
			String verbosityFlag = null;
			String[] stateArray = new String[19];
			if (initialState != null) {
				// separate the values by commas
				String[] stringArrayFromInitialState = initialState.split(",");
				for (int i = 0; i < 16; i++)
				{
					stateArray[i] = stringArrayFromInitialState[i + 3];
				}
				outputFlag = stringArrayFromInitialState[0];
				queueFlag = stringArrayFromInitialState[1];
				verbosityFlag = stringArrayFromInitialState[2];
			}
			else {
				// store all positions
				String[] args = {"S", "P", "Q", "1", "2", "3", "4", "5", "6", "15", "8", "9", "10", "11", "7", "13", "14", "12", "0"};
				for (int i = 0; i < 16; i++)
				{
					stateArray[i] = args[i + 3];
				}
				outputFlag = args[0];
				queueFlag = args[1];
				verbosityFlag = args[2];
			}
			// create the initial PuzzleState
			PuzzleState puzzleState = new PuzzleState(stateArray);
			// create the PuzzleSolver (the graph)
			PuzzleSolver puzzleSolver = new PuzzleSolver();
			// initialize the PuzzleSolver with the initial PuzzleState
			puzzleSolver.init(outputFlag, queueFlag, verbosityFlag);
			// prepare for capturing output of PuzzleSolver
			ByteArrayOutputStream newOut = new ByteArrayOutputStream();
			PrintStream originalOut = System.out;
			System.setOut(new PrintStream(newOut));
			// run the breadthFirstSearch() with the initial puzzle state
			puzzleSolver.breadthFirstSearch(puzzleState);
			// set output back to standard output
			System.setOut(originalOut);
			String result = newOut.toString();
			StepPath stepPath = new StepPath(result);
			return stepPath;
		}
	}
}
