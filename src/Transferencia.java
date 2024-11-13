public class Transferencia {
    // Atributos
    private String aliasDEBITO;
    private String aliasCREDITO;
    private String cbuDEBITO;
    private String cbuCREDITO;
    private double importe;
    private String concepto;
    private String motivo;
    private String referencia;
    private String email;
    private String titular;
    private String estado;

    // Constructor
    public Transferencia() {
    }

    public Transferencia(String aliasDEBITO, String aliasCREDITO, String cbuDEBITO, String cbuCREDITO, double importe, String concepto, String motivo, String referencia, String email, String titular, String estado) {
        this.aliasDEBITO = aliasDEBITO;
        this.aliasCREDITO = aliasCREDITO;
        this.cbuDEBITO = cbuDEBITO;
        this.cbuCREDITO = cbuCREDITO;
        this.importe = importe;
        this.concepto = concepto;
        this.motivo = motivo;
        this.referencia = referencia;
        this.email = email;
        this.titular = titular;
        this.estado = estado;
    }

    // Getters and setters
    public String getAliasDEBITO() {
        return aliasDEBITO;
    }

    public void setAliasDEBITO(String aliasDEBITO) {
        this.aliasDEBITO = aliasDEBITO;
    }

    public String getAliasCREDITO() {
        return aliasCREDITO;
    }

    public void setAliasCREDITO(String aliasCREDITO) {
        this.aliasCREDITO = aliasCREDITO;
    }

    public String getCbuDEBITO() {
        return cbuDEBITO;
    }

    public void setCbuDEBITO(String cbuDEBITO) {
        this.cbuDEBITO = cbuDEBITO;
    }

    public String getCbuCREDITO() {
        return cbuCREDITO;
    }

    public void setCbuCREDITO(String cbuCREDITO) {
        this.cbuCREDITO = cbuCREDITO;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}