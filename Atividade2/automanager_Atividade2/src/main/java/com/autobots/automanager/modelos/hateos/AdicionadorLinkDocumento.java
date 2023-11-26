package com.autobots.automanager.modelos.hateos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelos.hateos.geradorLink.GeraLinkDocumento;

@Component
public class AdicionadorLinkDocumento implements AdicionadorLink<Documento> {

	@Autowired
	private GeraLinkDocumento geraLink;
	
	@Override
	public void adicionarLink(List<Documento> lista) {
		for (Documento documento : lista) {
			long id = documento.getId();
			Link link = geraLink.atualizaDocumento(documento);
			documento.add(link);
			link = geraLink.pegarDocumento(id);
			documento.add(link);
			link = geraLink.listarTodosDocumentos();
			documento.add(link);
		}
	}

	@Override
	public void adicionarLink(Documento documento) {
		Link link = geraLink.atualizaDocumento(documento);
		documento.add(link);
		link = geraLink.pegarDocumento(documento.getId());
		documento.add(link);
		link = geraLink.listarTodosDocumentos();
		documento.add(link);
	}

	public void adicionarLink(long cliente_id, Documento documento) {
		Link link = geraLink.atualizaDocumento(documento);
		documento.add(link);
		link = geraLink.cadastrarDocumento(cliente_id, documento);
		documento.add(link);
		link = geraLink.deletaDocumento(documento.getId(), cliente_id);
		documento.add(link);
		link = geraLink.pegarDocumento(documento.getId());
		documento.add(link);
		link = geraLink.listarTodosDocumentos();
		documento.add(link);
		link = geraLink.pegarDocumentosCliente(cliente_id);
		documento.add(link);
	}
	
	public void adicionarLink(long cliente_id, List<Documento> documentos) {
		for (Documento documento : documentos) {
			Link link = geraLink.atualizaDocumento(documento);
			documento.add(link);
			link = geraLink.cadastrarDocumento(cliente_id, documento);
			documento.add(link);
			link = geraLink.deletaDocumento(documento.getId(), cliente_id);
			documento.add(link);
			link = geraLink.pegarDocumento(documento.getId());
			documento.add(link);
			link = geraLink.listarTodosDocumentos();
			documento.add(link);
			link = geraLink.pegarDocumentosCliente(cliente_id);
			documento.add(link);
		}
	}
}