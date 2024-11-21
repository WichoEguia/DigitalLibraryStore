package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Prestamo;
import com.example.DigitalLibraryStore.interfaces.IPrestamoService;
import com.example.DigitalLibraryStore.repositories.PrestamoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoServiceImpl implements IPrestamoService {
    private PrestamoDao prestamoDao;

    @Autowired
    public PrestamoServiceImpl(PrestamoDao prestamoDao) {
        this.prestamoDao = prestamoDao;
    }

    @Override
    public List<Prestamo> getPrestamoByUsuarioId(Long usuarioId) {
        return prestamoDao.findByUsuarioId(usuarioId);
    }
}
