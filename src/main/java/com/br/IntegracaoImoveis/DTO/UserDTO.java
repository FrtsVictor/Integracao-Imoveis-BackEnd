package com.br.IntegracaoImoveis.DTO;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.IntegracaoImoveis.model.Imovel;

public class UserDTO {

	private String nome;
	private	Set<ImovelDTO> imoveis = new HashSet<>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Set<ImovelDTO> getImoveis() {
		return imoveis;
	}
	public void setImoveis(Set<ImovelDTO> imoveis) {
		this.imoveis = imoveis;
	}
	public UserDTO(String nome, Set<ImovelDTO> imoveis) {
		super();
		this.nome = nome;
		this.imoveis = imoveis;
	}
	
	
	
	@Autowired
	private ModelMapper modelMapper;
	

	private ImovelDTO DTOtoModel(Imovel imovel) {
		return modelMapper.map(imovel, ImovelDTO.class); 
	}


	
	private Set<ImovelDTO> toCollectionDTO (Set<Imovel> lista){
		Set<ImovelDTO> lista2 = new HashSet<>();		 
		 for(Imovel imv : lista) {
			 ImovelDTO imvs = DTOtoModel(imv);
			 lista2.add(imvs);
		 }
		 return lista2;
	}	
	
	
}
