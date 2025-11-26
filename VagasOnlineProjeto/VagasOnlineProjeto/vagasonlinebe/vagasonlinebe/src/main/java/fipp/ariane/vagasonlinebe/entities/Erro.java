package fipp.ariane.vagasonlinebe.entities;

public class Erro {
    private String msg;

    public Erro(String msg) {
        this.msg = msg;
    }

    public Erro() {
        this("");
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Erro{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
