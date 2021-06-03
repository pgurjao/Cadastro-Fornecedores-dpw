package br.edu.infnet.domain.fornecedores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fornecedores", catalog = "provaarmenio", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"razao_social"}),
    @UniqueConstraint(columnNames = {"cnpj"}),
    @UniqueConstraint(columnNames = {"nome_fantasia"})})
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findById", query = "SELECT f FROM Fornecedor f WHERE f.id = :id"),
    @NamedQuery(name = "Fornecedor.findByRazaoSocial", query = "SELECT f FROM Fornecedor f WHERE f.razaoSocial = :razaoSocial"),
    @NamedQuery(name = "Fornecedor.findByNomeFantasia", query = "SELECT f FROM Fornecedor f WHERE f.nomeFantasia = :nomeFantasia"),
    @NamedQuery(name = "Fornecedor.findByCnpj", query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj")})
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "O campo razao social eh obrigatorio")
    @Size(min = 1, max = 50, message = "O campo razao social deve ter entre 1 e 50 caracteres")
    @Column(name = "razao_social", nullable = false, length = 50)
    private String razaoSocial;
    @Basic(optional = false)
    @NotNull(message = "O nome fantasia eh obrigatorio")
    @Size(min = 1, max = 50, message = "O campo nome fantasia deve ter entre 1 e 50 caracteres")
    @Column(name = "nome_fantasia", nullable = false, length = 50)
    private String nomeFantasia;
    @Basic(optional = false)
    @NotNull(message = "O campo CNPJ eh obrigatorio")
    @Size(min = 1, max = 14, message = "O campo CNPJ deve possuir 14 digitos e conter apenas numeros")
    @Column(nullable = false, length = 14)
    private String cnpj;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedor")
    private List<Contato> contatos;

    public Fornecedor() {
    }

    public Fornecedor(Integer id) {
        this.id = id;
    }

    public Fornecedor(Integer id, String razaoSocial, String nomeFantasia, String cnpj) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.infnet.domain.fornecedores.Fornecedor[ id=" + id + " ]";
    }
    
    public String toText() {
        return "Fornecedor{" + "id=" + id + ", razaoSocial=" + razaoSocial + ", nomeFantasia=" + nomeFantasia + ", cnpj=" + cnpj + '}';
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
    
}
