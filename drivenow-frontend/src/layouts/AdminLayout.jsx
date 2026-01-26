import { Outlet } from "react-router-dom";
import React from "react";
import AdminNavbar from "../components/Admin/AdminNavbar/Navbar";

        const AdminLayout = () => {
            return (
                <> 
                <AdminNavbar/>
                <div style={{ padding: "20px" }}>
                <Outlet />  
                </div>
                </>
            )
        }
    export default AdminLayout;