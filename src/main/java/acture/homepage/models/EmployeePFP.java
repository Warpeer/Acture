package acture.homepage.models;

public class EmployeePFP {
    private int id;
    private byte[] image_data;
    private String file_type;
    private int eId;

    public EmployeePFP(int id, byte[] image_data, String file_type, int eId) {
        this.id = id;
        this.image_data = image_data;
        this.file_type = file_type;
        this.eId = eId;
    }

    public EmployeePFP(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage_data() {
        return image_data;
    }

    public void setImage_data(byte[] image_data) {
        this.image_data = image_data;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }
}
