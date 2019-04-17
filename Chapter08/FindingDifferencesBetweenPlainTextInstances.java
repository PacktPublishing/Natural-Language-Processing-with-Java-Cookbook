package com.packt.javanlp.cookbook.chapter9;

import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

public class FindingDifferencesBetweenPlainTextInstances {

	public static void main(String[] args) {
		// Finding differences between plain text instances
		DiffRowGenerator diffRowGenerator = DiffRowGenerator.create().showInlineDiffs(true).mergeOriginalRevised(true)
				.inlineDiffByWord(true).oldTag(f -> "-").newTag(f -> "^").build();

		try {
			List<DiffRow> diffRowList = diffRowGenerator.generateDiffRows(Arrays.asList("A simple sentence."),
					Arrays.asList("Also, a simple sentence."));
			System.out.println(diffRowList.get(0).getOldLine());
		} catch (DiffException e) {
			// Handle exceptions
		}

		try {
			List<DiffRow> diffRowList = diffRowGenerator.generateDiffRows(
					Arrays.asList("Start with a clean pot.", "Add the good ingredients."),
					Arrays.asList("Start with a clean pot.", "Add the best ingredients.", "Don't forget to stir."));

			for (DiffRow diffRow : diffRowList) {
				System.out.println("Old Line: " + diffRow.getOldLine());
				System.out.println("New Line:" + diffRow.getNewLine());
				System.out.println();
			}
		} catch (DiffException e) {
			// Handle exceptions
		}

		try {
			List<String> file1List = Files.readAllLines(new File("File1.txt").toPath());
			List<String> file2List = Files.readAllLines(new File("File2.txt").toPath());

			for (int i = 0; i < file1List.size(); i++) {
				Patch<String> stringPatch = DiffUtils.diffInline(file1List.get(i), file2List.get(i));
				System.out.println("Line: " + (i + 1));
				for (AbstractDelta<String> stringDelta : stringPatch.getDeltas()) {
					System.out.println("\tDelta Type: " + stringDelta.getType() + "\n\t\tSource - "
							+ stringDelta.getSource() + "\n\t\tTarget - " + stringDelta.getTarget());
				}
			}

		} catch (IOException ex) {
			// Handle exceptions
		} catch (DiffException ex) {
			// Handle exceptions
		}
	}

}
