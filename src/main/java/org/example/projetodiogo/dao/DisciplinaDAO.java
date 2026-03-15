    package org.example.projetodiogo.dao;

    import org.example.projetodiogo.exceptions.DataAccessException;
    import org.example.projetodiogo.exceptions.EntityNotFoundException;
    import org.example.projetodiogo.model.Aluno;
    import org.example.projetodiogo.model.Boletim;
    import org.example.projetodiogo.model.Disciplina;
    import org.example.projetodiogo.util.ConnectionFactory;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    public class DisciplinaDAO {

        public Disciplina buscarPorId(int idDisciplina) {

            String sql = """
                    SELECT nome, id_professor FROM disciplinas
                    WHERE id_disciplina = ?
        """;

            Connection conn = null;
            PreparedStatement pstmt = null;
            Disciplina disciplina = new Disciplina();

            try {
                conn = ConnectionFactory.conectar();
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, idDisciplina);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    disciplina.setNome(rs.getString("nome"));
                    disciplina.setIdProfessor(rs.getInt("id_professor"));
                    return disciplina;
                }

            } catch (SQLException e) {
                System.out.println("[DAO] Erro ao visualizar o boletim: " + e.getMessage());
            } finally {
                try {
                    if (conn != null) ConnectionFactory.desconectar(conn);
                    if (pstmt != null) pstmt.close();
                } catch (SQLException e) {
                    throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
                }
            }
            return disciplina;
        }

        public Optional<Disciplina> buscarPorIdProfessor(int idProfessor) {

            String query = """
                    SELECT d.nome FROM disciplinas d
                    WHERE id_professor = ?
                    """;

            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conn = ConnectionFactory.conectar();
                ps = conn.prepareStatement(query);
                ps.setInt(1, idProfessor);

                rs = ps.executeQuery();

                if (rs.next()) {
                    Disciplina disciplina = new Disciplina(
                            rs.getInt("id_disciplina"),
                            rs.getString("nome"),
                            rs.getInt("id_professor")
                    );

                    return Optional.of(disciplina);
                } else {
                    throw new EntityNotFoundException("Disciplina", rs.getInt("id_disciplina"));
                }
            } catch (SQLException e) {
                System.out.println("[DAO] Erro ao buscas disciplina: " + e.getMessage());
                e.printStackTrace(System.err);
                throw new DataAccessException("Erro ao buscar aluno", e);
            } finally {
                try {
                    if (conn != null) ConnectionFactory.desconectar(conn);
                    if (ps != null) ps.close();
                    if (rs != null) rs.close();
                } catch (SQLException e) {
                    throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
                }
            }
        }

        public ArrayList<Disciplina> visualizarDisciplinas() {

            String sql = "SELECT * FROM disciplinas ORDER BY id_disciplina;";

            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = ConnectionFactory.conectar();
                pstmt = conn.prepareStatement(sql);

                ResultSet rs = pstmt.executeQuery();

                ArrayList<Disciplina> disciplinasList = new ArrayList<>();

                while (rs.next()) {
                    Disciplina disciplina = new Disciplina(
                            rs.getInt("id_disciplina"),
                            rs.getString("nome"),
                            rs.getInt("id_professor")
                    );

                    disciplinasList.add(disciplina);
                }
                return disciplinasList;
            } catch (SQLException e) {
                System.out.println("[DAO] Erro ao visualizar o boletim: " + e.getMessage());
            } finally {
                try {
                    if (conn != null) ConnectionFactory.desconectar(conn);
                    if (pstmt != null) pstmt.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar as conexões do Banco: " + e.getMessage());
                }
            }
            return null;
        }
        public ArrayList<Disciplina> buscarDisciplinasPorAluno(int idAluno) {

            String sql = """
            SELECT d.*
            FROM disciplinas d
            JOIN notas n ON n.id_disciplina = d.id_disciplina
            WHERE n.id_aluno = ?
        """;

            ArrayList<Disciplina> lista = new ArrayList<>();

            try (Connection conn = ConnectionFactory.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, idAluno);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {

                    Disciplina d = new Disciplina();

                    d.setId(rs.getInt("id_disciplina"));
                    d.setNome(rs.getString("nome"));
                    d.setIdProfessor(rs.getInt("id_professor"));

                    lista.add(d);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return lista;
        }

        public List<Boletim> visualizarPorDisciplina(int idAluno, int idDisciplina) {
            String sql = """
            SELECT * FROM boletim
            WHERE id_aluno = ? AND id_disciplina = ?
        """;

            List<Boletim> lista = new ArrayList<>();

            try (Connection conn = ConnectionFactory.conectar();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, idAluno);
                ps.setInt(2, idDisciplina);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Boletim boletim = new Boletim(
                            rs.getInt("id_boletim"),
                            rs.getInt("id_aluno"),
                            rs.getInt("id_disciplina"),
                            rs.getDouble("nota")
                    );

                    lista.add(boletim);
                }

            } catch (SQLException e) {
                throw new DataAccessException("Erro ao buscar boletim", e);
            }

            return lista;
        }
        public List<Disciplina> buscarPorNome(String nome) {

            String sql = """
            SELECT * FROM disciplinas
            WHERE LOWER(nome) LIKE ?
        """;

            List<Disciplina> lista = new ArrayList<>();

            try (Connection conn = ConnectionFactory.conectar();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, "%" + nome.toLowerCase() + "%");

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Disciplina d = new Disciplina(
                            rs.getInt("id_disciplina"),
                            rs.getString("nome"),
                            rs.getInt("id_professor")
                    );
                    lista.add(d);
                }

            } catch (SQLException e) {
                throw new DataAccessException("Erro ao buscar disciplina por nome", e);
            }

            return lista;
        }

        public void delete(int id){

            String sql = "DELETE FROM disciplinas WHERE id_disciplina = ?";

            try (Connection conn = ConnectionFactory.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);

                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void atualizarProfessorPorNome(int idDisciplina, String nomeProfessor) throws SQLException {

            String sqlBuscarProfessor = """
            SELECT p.id_professor
            FROM professores p
            JOIN usuarios u ON u.id_usuario = p.id_usuario
            WHERE LOWER(TRIM(u.nome)) = LOWER(TRIM(?))
            LIMIT 1
        """;

            String sqlVerificarVinculo = """
            SELECT id_disciplina FROM disciplinas
            WHERE id_professor = ? AND id_disciplina != ?
        """;

            String sqlAtualizar = "UPDATE disciplinas SET id_professor = ? WHERE id_disciplina = ?";

            try (Connection conn = ConnectionFactory.conectar()) {

                Integer idProfessor = null;
                try (PreparedStatement ps = conn.prepareStatement(sqlBuscarProfessor)) {
                    ps.setString(1, nomeProfessor);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        idProfessor = rs.getInt("id_professor");
                    }
                }

                if (idProfessor == null) {
                    throw new SQLException("Professor não encontrado: " + nomeProfessor);
                }

                try (PreparedStatement ps = conn.prepareStatement(sqlVerificarVinculo)) {
                    ps.setInt(1, idProfessor);
                    ps.setInt(2, idDisciplina);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        throw new SQLException("Professor já vinculado a outra disciplina.");
                    }
                }

                try (PreparedStatement ps = conn.prepareStatement(sqlAtualizar)) {
                    ps.setInt(1, idProfessor);
                    ps.setInt(2, idDisciplina);
                    ps.executeUpdate();
                }
            }
        }

        public void inserir(String nomeDisciplina, String nomeProfessor) throws SQLException {

            String sqlBuscarProfessor = """
                    SELECT p.id_professor
                    FROM professores p
                    JOIN usuarios u ON u.id_usuario = p.id_usuario
                    WHERE LOWER(TRIM(u.nome)) = LOWER(TRIM(?))
                    LIMIT 1
                """;

            String sqlVerificarVinculo = """
                    SELECT id_disciplina FROM disciplinas
                    WHERE id_professor = ?
                """;

            String sqlInserir = "INSERT INTO disciplinas (nome, id_professor) VALUES (?, ?)";

            try (Connection conn = ConnectionFactory.conectar()) {

                Integer idProfessor = null;
                try (PreparedStatement ps = conn.prepareStatement(sqlBuscarProfessor)) {
                    ps.setString(1, nomeProfessor);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        idProfessor = rs.getInt("id_professor");
                    }
                }

                if (nomeProfessor == null || nomeProfessor.trim().isEmpty()) {
                    try (PreparedStatement ps = conn.prepareStatement(sqlInserir)) {
                        ps.setString(1, nomeDisciplina);
                        ps.setNull(2, java.sql.Types.INTEGER);
                        ps.executeUpdate();
                    }
                    return;
                }

                if (idProfessor == null) {
                    throw new SQLException("Professor não encontrado: " + nomeProfessor);
                }

                try (PreparedStatement ps = conn.prepareStatement(sqlVerificarVinculo)) {
                    ps.setInt(1, idProfessor);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        throw new SQLException("Professor já vinculado a outra disciplina.");
                    }
                }

                try (PreparedStatement ps = conn.prepareStatement(sqlInserir)) {
                    ps.setString(1, nomeDisciplina);
                    ps.setInt(2, idProfessor);
                    ps.executeUpdate();
                }
            }
        }
    }