package dev.dommi.gameserver.backend.plugin.database.report;

import dev.dommi.gameserver.backend.adapter.database.report.Report;
import dev.dommi.gameserver.backend.adapter.database.report.ReportDatabaseController;
import dev.dommi.gameserver.backend.adapter.database.report.ReportType;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class ReportDatabaseControllerImpl implements ReportDatabaseController {

    private static final Logger logger = Logger.getLogger(ReportDatabaseControllerImpl.class.getName());
    private final ReportTableWrapper reportTableWrapper;
    private final ReportTypeTableWrapper reportTypeTableWrapper;

    public ReportDatabaseControllerImpl() {
        reportTableWrapper = new ReportTableWrapper(MariaDBConnector.getInstance());
        reportTypeTableWrapper = new ReportTypeTableWrapper(MariaDBConnector.getInstance());
    }

    @Override
    public Collection<Report> findAll() {

        try {

            return reportTableWrapper.findAll();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }


    @Override
    public Collection<Report> getReportsCreatedBy(int userId) {
        try {

            return reportTableWrapper.findReportsCreatedBy(userId);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }


    @Override
    public Collection<Report> getReportsFor(int userId) {
        try {

            return reportTableWrapper.findReportsFor(userId);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }


    @Override
    public Report findById(int reportId) {
        try {

            return reportTableWrapper.findById(reportId);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }


    @Override
    public Collection<ReportType> getReportTypes() {
        try {

            return reportTypeTableWrapper.findAll();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public ReportType getReportType(int id) {
        try {

            return reportTypeTableWrapper.findById(id);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public int getReportTypeIdByName(String name) {
        try {

            return reportTypeTableWrapper.findIdByName(name);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return -1;
    }


    @Override
    public boolean update(Report value) {
        try {

            reportTableWrapper.update(value);
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean create(Report value) {
        try {

            reportTableWrapper.create(value);
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }


    @Override
    public boolean delete(int value) {
        try {

            reportTableWrapper.deleteById(value);
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }


}
