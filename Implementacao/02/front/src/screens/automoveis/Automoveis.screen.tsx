import { useEffect, useState } from "react";
import "./AutomoveisScreen.css";
import { getAutomoveis } from "../../services/automoveis.service";

export const AutomoveisScreen = () => {
  const [automoveis, setAutomveis] = useState([]);
  const [tipoDeFiltro, setTipoDeFiltro] = useState("TODOS");

  const quantityOfAutomoveis = automoveis.length;
  const productsTableHeaders = ["Modelo", "Placa", "Marca", "Ano", "", ""];

  const getAutomoveisLocal = async (tipo: string) => {
    let result = [];
    if (tipo === "TODOS") {
      result = await getAutomoveis();
    } else {
      result = await getAutomoveis(tipo);
    }
    setAutomveis(result);
  };

  useEffect(() => {
    console.log("a");
    getAutomoveisLocal(tipoDeFiltro);
  }, [tipoDeFiltro]);

  return (
    <div className="admin-list-of-products-main-container">
      <div className="admin-list-of-products-first-row-container">
        <h1>Automóveis</h1>
      </div>
      <div className="admin-list-of-products-outline-container">
        <select
          onChange={(ev) => {
            console.log("aaa");
            setTipoDeFiltro(ev.target.value);
          }}
        >
          <option value="TODOS">TODOS</option>
          <option value="DISPONIVEIS">DISPONIVEIS</option>
          <option value="ALUGADOS">ALUGADOS</option>
        </select>
        <div className="admin-list-of-products-table-container">
          {quantityOfAutomoveis !== 0 ? (
            <table className="admin-list-of-products-table">
              <thead className="admin-list-of-products-header-gradient">
                {productsTableHeaders.map((header) => (
                  <th>{header}</th>
                ))}
              </thead>
              <tbody>
                {automoveis.map((automovel) => (
                  <tr className="admin-list-of-prooducts-product-row">
                    <td>{automovel.placa}</td>
                    <td>{automovel.modelo}</td>
                    <td>{automovel.marca}</td>
                    <td>{automovel.ano}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : null}
        </div>
      </div>
    </div>
  );
};
