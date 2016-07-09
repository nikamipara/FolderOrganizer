import java.io.File;
import java.util.Scanner;

public class MainClass {
	public static void main(String... args) {
		Scanner s = new Scanner(System.in);
		String root = s.nextLine();
		organize(root);
		System.out.println("Oraganize 100%.");
	}

	private static void organize(String rootPath) {
		File root = new File(rootPath);
		if (root.exists() && root.canWrite() && root.isDirectory()) {
			File[] files = root.listFiles();
			for (File item : files) {
				String name;
				if (!item.isDirectory()) {
					File parent = item.getParentFile();
					name = formatName(item.getName());
					if (name.charAt(0) == '.')
						continue;
					File newDir = new File(parent,name);
					newDir.mkdir();
					File newFile = new File(newDir,item.getName());
					item.renameTo(newFile);
					System.out.println(name + ": " + parent.getName());
				}
			}
		} else {
			System.out.println("Error in path Please provide valid path.");
		}

	}

	private static String formatName(String name) {
		int index = name.lastIndexOf(".");
		if (index > 0)
			return name.substring(0, index);
		else
			return name + "1";
	}
}
