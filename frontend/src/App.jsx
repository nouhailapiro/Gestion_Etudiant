import React from 'react';
import { Routes, Route } from 'react-router-dom';
import EtudiantTable from './components/EtudiantsTable';
import Notes from './components/Notes'
import AjouterNote from './components/AjouterNotes'

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<EtudiantTable />} />
      <Route path="/etudiants/:id" element={<Notes />} />
      <Route path="/etudiant/:id/ajouterNote" element={<AjouterNote />} />
    </Routes>
  );
};

export default App;
