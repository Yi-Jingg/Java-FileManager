import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileManager {

    public void listFiles(String path){
        File folder = new File(path);
        if (folder.exists()){
            for (File f : folder.listFiles()) {
                if (f.getName().endsWith(".txt")){
                    System.out.println(f.getName());
                }
            }

        } else {
            System.out.println("Folder cannot be found");
        }
    }
    public void createFile(String folderPath, String fileName){
        File newFile = new File(folderPath, fileName);
        if (newFile.exists()){
            System.out.println("File already exists");
        } else {
            try{
                if (newFile.createNewFile()){
                    System.out.println("File created successfully");
                } else {
                    System.out.println("Fail to create file");
                }
            } catch (Exception e) {
                System.out.println("Error creating file:" + e.getMessage());
            }
        }
    }

    public boolean deleteFile(String fileName){
        File deleteFile = new File(fileName);
        try {
            if (deleteFile.exists()){
                if (deleteFile.delete()) {
                    System.out.println("File successfully deleted!");
                    return true;
                } else {
                    System.out.println("Failed to delete file");
                    return false;
                }
            } else {
                System.out.println("File does not exist");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error deleting file: " + e.getMessage());
            return false;
        }
    }

    public boolean renameFile(String oldPath, String newName){
        File oldFile = new File(oldPath);
        if (!oldFile.exists()){
            System.out.println("File does not exist");
            return false;
        }
        String folder = oldFile.getParent();
        File newFile = new File(folder,newName);

        if (newFile.exists()) {
            System.out.println("A file with that name already exists");
            return false;
        }
        if (oldFile.renameTo(newFile)){
            System.out.println("The file name has been changed");
            return true;
        } else {
            System.out.println("Something when wrong");
            return false;
        }

    }
    public void searchFiles(String folderPath, String keyword){
        File folder = new File(folderPath);
        if (folder.exists()){

            for (File f : folder.listFiles()){
                if (f.getName().endsWith(".txt")){
                    try{
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);
                        String line;
                        while ((line = br.readLine()) != null) {  // Keep reading until end
                            if (line.contains(keyword)) {
                                System.out.println("Found in: " + f.getName());
                                break;  // Found it! Move to next file
                            }
                        }
                        br.close();
                    } catch (Exception e){
                        System.out.println("Error reading file" + e.getMessage());
                    }
                }
            }
        }
    }
}
