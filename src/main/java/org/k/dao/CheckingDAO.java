package org.k.dao;

import org.k.domain.Checking;

import java.util.List;

public interface CheckingDAO {
    public int addChecking(Checking checking);
    public void updateChecking(Checking checking);
    public void deleteChecking(Checking checking);
    public List<Checking> getAllCheckings();
    public Checking getCheckingById(int id);
}
