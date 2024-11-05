package Facu;

public class AliasCliente {
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
    
    public AliasCliente(String aliasDEBITO, String aliasCREDITO, String cbuDEBITO, String cbuCREDITO, double importe, String concepto, String motivo, String referencia, String email, String titular) {
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
    }

    public String getAliasDEBITO() {
        return aliasDEBITO;
    }

    public String getAliasCREDITO() {
        return aliasCREDITO;
    }

    public String getCbuDEBITO() {
        return cbuDEBITO;
    }

    public String getCbuCREDITO() {
        return cbuCREDITO;
    }

    public double getImporte() {
        return importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getEmail() {
        return email;
    }

    public String getTitular() {
        return titular;
    }

    
}
