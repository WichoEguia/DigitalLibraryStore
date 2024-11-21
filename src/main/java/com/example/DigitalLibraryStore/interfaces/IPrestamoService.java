package com.example.DigitalLibraryStore.interfaces;

import com.example.DigitalLibraryStore.entities.Prestamo;

import java.util.List;

public interface IPrestamoService {
    List<Prestamo> getPrestamoByUsuarioId(Long usuarioId);
}
