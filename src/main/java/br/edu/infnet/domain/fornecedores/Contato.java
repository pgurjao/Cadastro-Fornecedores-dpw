package br.edu.infnet.domain.fornecedores;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contatos", catalog = "provaarmenio", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@NamedQueries({
    @NamedQuery(name = "Contato.findAll", query = "SELECT c FROM Contato c"),
    @NamedQuery(name = "Contato.findById", query = "SELECT c FROM Contato c WHERE c.id = :id"),
    @NamedQuery(name = "Contato.findByNome", query = "SELECT c FROM Contato c WHERE c.nome = :nome"),
    @NamedQuery(name = "Contato.findByEmail", query = "SELECT c FROM Contato c WHERE c.email = :email"),
    @NamedQuery(name = "Contato.findByFone", query = "SELECT c FROM Contato c WHERE c.fone = :fone"),
    @NamedQuery(name = "Contato.findByIdFornecedor", query = "SELECT c FROM Contato c WHERE c.fornecedor = :fornecedor")})
public class Contato implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @NotNull(message = "O campo nome eh obrigatorio")
    @Size(min = 1, max = 50, message = "O campo nome deve ter entre 1 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nome;
    
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Email invalido")
    @Basic(optional = false)
    @NotNull(message = "O campo email eh obrigatorio")
    @Size(min = 1, max = 50, message = "O campo email deve ter entre 1 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String email;
    
    @Basic(optional = false)
    @NotNull(message = "O campo telefone eh obrigatorio")
    @Size(min = 1, max = 20, message = "O campo telefone deve ter entre 1 e 20 caracteres")
    @Column(nullable = false, length = 20)
    private String fone;
    
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Fornecedor fornecedor;

    public Contato() {
    }

    public Contato(Integer id) {
        this.id = id;
    }

    public Contato(Integer id, String nome, String email, String fone, Fornecedor idFornecedor) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
        this.fornecedor = idFornecedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.strip();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.strip();
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone.strip();
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
        if (!(object instanceof Contato)) {
            return false;
        }
        Contato other = (Contato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.infnet.domain.fornecedores.Contato[ id=" + id + " ]";
    }
    
    public String toText() {
        return "Contato{" + "id=" + id + ", Nome=" + nome + ", Email=" + email + ", Telefone=" + fone + '}';
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
}
