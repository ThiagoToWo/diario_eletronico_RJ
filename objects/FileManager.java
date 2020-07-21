package objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

public class FileManager {
	public Object desserializarComFileChooser() {
		JFileChooser load = new JFileChooser();
		load.showOpenDialog(null);
		File arquivo = load.getSelectedFile();
		Object obj = null;
		try {
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream(arquivo));
			obj =  oi.readObject();
			oi.close();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro! " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Ocorreu um erro! " + e.getMessage());
		}			
		return obj;
	}
	
	public void serializarComFileChooser(Object obj) {
		JFileChooser save = new JFileChooser();
		save.showSaveDialog(null);
		File arquivo = save.getSelectedFile();
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arquivo));
			os.writeObject(obj);
			os.close();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro! " + e.getMessage());
			return;
		}
	}
	
	
}
